# Local Backend Setup

Before running the backend locally, make sure to clone the database data repository:

```bash
git clone git@github.com:kenu21/cafe-catalog-data.git
```

To run the backend locally, please create a `.env` file in the root of the project. It should look like this:

```env
MYSQL_DATABASE=coffee_shop
MYSQL_ROOT_PASSWORD=
MYSQL_USER=
MYSQL_PASSWORD=
MYSQL_DATABASE_URL="jdbc:mysql://db:${MYSQL_CONTAINER_PORT}/${MYSQL_DATABASE}?allowPublicKeyRetrieval=true&useSSL=false&allowLoadLocalInfile=true&allowLocalInfile=true"

MYSQL_LOCAL_PORT=3307
MYSQL_CONTAINER_PORT=3306

CONTEXT_PATH=/api/v1

BACKEND_LOCAL_PORT=8081
BACKEND_CONTAINER_PORT=80

FRONTEND_LOCAL_PORT=81

COMPOSE_EXTENDS_PATH=
```

Fill in the required values in the file.

Next, in the root of the project, run:

```bash
docker compose up -d
```

After that, you can make queries like this:

```bash
http://localhost:8081/api/v1/cafes
```
