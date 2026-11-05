# Progreso de la migración a DDD

Registro vivo de la migración de la estructura antigua (`controller/`, `service/`, `repository/`, `model/`, `DTO/`, `validation/`, `mapper/`) a bounded contexts DDD bajo `com.dataproject.yorha`, siguiendo `CLAUDE.md`. Actualizar al final de cada sesión.

## Forma de trabajar

- Claude actúa como **tutor**, en español: explica el porqué de cada patrón con ejemplos del propio código, propone un paso cada vez con pistas o esqueletos, y el usuario escribe el código. Cuando el usuario dice "listo", Claude revisa el archivo.
- Si el usuario no acierta, Claude da **otra pista, no la solución**.
- Commits: solo la línea de título en Conventional Commits (`feat(android): ...`), sin cuerpo ni `Co-Authored-By`. No incluir `.idea/`. Se commitea en `main`.
- Las clases antiguas se mantienen hasta que el camino nuevo las sustituye por completo; después se borran.

## Decisiones tomadas

- **Agregado `Android`**: constructor privado y dos fábricas estáticas.
  - `create(...)`: androide nuevo; id `null`, `Status.ACTIVE`, sin operador. Lo usan los casos de uso.
  - `restore(...)`: reconstrucción desde BD con todos los campos tal cual. Solo lo usa infraestructura.
- **Ids (opción A)**: el id es `null` hasta guardar, porque lo genera MySQL. `save()` devuelve un `Android` nuevo ya con id. `new AndroidId(null)` lanza excepción; una referencia `AndroidId` nula significa "aún no guardado".
- **`Entity`**: `@Getter` de Lombok (expone `getId()`); `equals` devuelve `false` si alguno de los ids es `null`.
- **Referencias entre agregados por id**: `AndroidEntity` guarda `@Column Long typeId` y `Long operatorId`, sin `@ManyToOne` ni lista `assignedAndroids`.
- **`number`** (siguiente número dentro del tipo): lo calcula `CreateAndroidUseCase` con `countByTypeId(typeId) + 1`, no `@PrePersist` ni `Type`.
- **`status` no nulo**: se garantiza con `@Column(nullable = false)` en `AndroidEntity`, no con validación en el dominio (decisión del usuario).

## Estado actual

Contexto en curso: **`android`**.

- [x] `android/domain`: `Android`, `AndroidId`, `TypeId`, `Status`, `AndroidRepository`
- [x] `android/infrastructure/AndroidEntity`
- [x] `android/infrastructure/AndroidJpaRepository` (`findAllByStatus(Status)`, `countByTypeId(Long)`)
- [ ] `android/infrastructure/AndroidRepositoryImpl` — **a medias, no compila**
  - `save()` incompleto, `toDomain` vacío, faltan `findById`, `findAll`, `findAllByActive`, `countByTypeId`.
  - `toEntity` usa el constructor de 4 parámetros de `AndroidEntity` y pierde `id`, `number`, `status` y `operatorId`. Hay que rellenar con setters (cuidando los `null` de `id` y `operatorId`) y borrar ese constructor.
- [ ] `android/application`: casos de uso y DTOs en `android/application/dto/`
- [ ] Mover `AndroidController` y `DashboardController` a `web/`
- [ ] `AndroidTest` de dominio, sin Spring
- [ ] Borrar las clases antiguas de `android` (`model/Android`, `repository/AndroidRepository`, `service/AndroidService`, `mapper/AndroidMapper`, DTOs antiguos)

Siguientes contextos: `type`, `weapon`, `user`.

## Pendiente de decidir / para más adelante

- `getName(char typeCode)` y `getShortName(char typeCode)` reciben el código del tipo desde fuera: decidir si el nombre se monta en la capa de aplicación (cargando el `Type`) o se deja así.
- Concurrencia en `number`: dos altas simultáneas del mismo tipo pueden obtener el mismo número. Posible restricción única `(type_id, number)`.