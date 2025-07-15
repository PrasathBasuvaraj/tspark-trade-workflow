# tspark-trade-workflow

TSpark Trade Workflow is a demonstration project showcasing the use-case of running a lightweight workflow engine for
stock trading operations. This project is built using Spring Boot and Flowable engine and provides a solid foundation
for building robust
applications.

## 🚀 Running the Project

- Clone the repo:

```bash
git clone https://github.com/TechSparkWorkspace/tspark-trade-workflow.git
cd tspark-trade-workflow
```

- Start the app

```bash
./gradlew bootRun
```

- Open Swagger UI to test APIs:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## ⚙️ Interacting with the Workflow

This project includes a dedicated controller (WorkflowController) to help you inspect and manage your running workflows
directly through REST APIs. You can access these endpoints via Swagger or Postman to streamline debugging and manual
flow control.

### 📌 Endpoints Overview

- GET `/api/workflow/instances` — List all active process instances.
- GET `/api/workflow/tasks` — View user tasks. You can filter by:
    - `?assignee=john` → tasks assigned to a user
    - `?candidateGroup=managers` → tasks waiting for a group
- POST `/api/workflow/tasks/{taskId}/complete` — Complete a user task manually. Accepts an optional JSON body to pass
  variables.