# Microservice Project with Monitoring

Welcome to our microservice project, which includes services like `config-server`, `student`, `school`, `gateway`, `discovery`, and `email-sender`. This README provides instructions for setting up monitoring with Prometheus and Grafana.

## Prerequisites

Before you begin, make sure you have the following software installed on your system:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Services

Here's a brief overview of the services included in this project:

- **config-server**: Centralized configuration service.
- **student**: Service for managing student-related data.
- **school**: Service for managing school-related data.
- **gateway**: API gateway service for routing requests.
- **discovery**: Service discovery and registration.
- **email-sender**: Service for sending emails.

## Monitoring Setup

We've included a monitoring setup using Prometheus and Grafana to help you keep track of your services. You can start these services with Docker Compose.

### Step 1: Clone the Repository

If you haven't already, clone this repository to your local machine:

```bash
git clone <https://github.com/thekarenovich/spring-boot-simple-microservices-thekarenovich.git>
```
```bash
cd <spring-boot-simple-microservices-thekarenovich>
```

### Step 2: Start the Services

Use the following command to start the monitoring services along with your microservices:

```bash
docker-compose up -d
```

This will start Prometheus, Grafana, a PostgreSQL database for Grafana's configuration, and more.

### Step 3: Access Prometheus

You can access Prometheus at [http://localhost:9090](http://localhost:9090) to monitor your services.

### Step 4: Access Grafana

Grafana can be accessed at [http://localhost:3000](http://localhost:3000). Use the following credentials to log in:

- **Username**: admin
- **Password**: password

### Step 5: Configure Grafana Datasource

To visualize your data, you need to configure a data source in Grafana:

1. Log in to Grafana (as mentioned in Step 4).
2. Go to "Configuration" in the left menu and click on "Data Sources."
3. Click on "Add data source" and choose Prometheus.
4. Configure the URL as `http://prometheus:9090`.
5. Click "Save & Test" to ensure the connection is successful.

You can now create dashboards and set up alerts to monitor your microservices effectively.

## Database Access

- **PostgreSQL**: A PostgreSQL database is included, accessible at `localhost:5432`. You can use tools like pgAdmin to manage your databases.

## Service Communication

Your services communicate via defined ports and endpoints. Make sure to configure your services accordingly to enable smooth communication between them.

## Questions and Issues

If you have any questions or encounter issues, please feel free to open an issue on this repository.

We hope you find this monitoring setup helpful in managing your microservice project!