# todo-app

Консольное TODO-приложение на Java: добавляйте, удаляйте, отмечайте и ищите задачи прямо в терминале.

## Функции и команды

Приложение принимает следующие команды:

| Команда | Описание |
| --- | --- |
| `add <задача>` | Добавить задачу (крайние пробелы обрезаются; пустые значения игнорируются) |
| `remove <индекс>` | Удалить задачу по индексу (с единицы) |
| `list` | Показать все задачи; выполненные помечаются как `[x]`, невыполненные — `[ ]` |
| `done <индекс>` | Отметить задачу выполненной |
| `search <текст>` | Найти задачи по подстроке без учёта регистра |
| `clear` | Удалить все задачи |
| `exit` | Завершить приложение |

## Требования

- JDK 23 (сборка нацелена на Java 23; локально достаточно JDK 23 или новее).
- Gradle Wrapper 9.8.0 включён в проект — отдельная установка Gradle не требуется.

## Сборка

```bash
./gradlew build
```

На Windows:

```powershell
.\gradlew build
```

Итоговый исполняемый JAR появляется в `build/libs/todo-app-<version>.jar`.

## Запуск

```bash
java -jar build/libs/todo-app-<version>.jar
```

Например, для версии `0.3.0`:

```powershell
java -jar "build/libs/todo-app-0.3.0.jar"
```

## Тесты

Проект использует JUnit 5. Запуск всех тестов:

```bash
./gradlew test
```

Полная проверка со сборкой:

```bash
./gradlew clean build
```

## GitHub Actions

### `CI / Build & Release`

Запускается при push в ветку `main`: собирает проект, выполняет тесты и создаёт GitHub Release `v<version>` с прикреплённым JAR-артефактом.

### `Publish to GitHub Packages (Maven)`

Запускается после успешного завершения `CI / Build & Release` и публикует fat-JAR в GitHub Packages (Maven) как `org.example:todo-app`.

### `Nightly Build`

Запускается ежедневно по расписанию (`cron 0 2 * * *`, UTC) или вручную через `workflow_dispatch`; выполняет только сборку и тесты — релизы и пакеты не создаёт.

## GitHub Flow

Работа ведётся по GitHub Flow:

1. От актуальной `main` создаётся отдельная ветка для задачи.
2. Вносятся изменения и коммиты.
3. Ветка отправляется на GitHub, создаётся Pull Request.
4. Проверки (CI, ревью) должны пройти успешно.
5. PR смерживается в `main`.
6. Локальная `main` обновляется через `git pull`.

## Ссылки

- Репозиторий: https://github.com/AbdulazizMirzakamilov/todo-app
- GitHub Actions: https://github.com/AbdulazizMirzakamilov/todo-app/actions
- Releases: https://github.com/AbdulazizMirzakamilov/todo-app/releases
- Packages: https://github.com/AbdulazizMirzakamilov?tab=packages