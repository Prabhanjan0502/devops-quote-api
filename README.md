# Quote API - DevOps Pipeline Project

A small Flask REST API used to demonstrate a CI/CD pipeline:
Git -> Jenkins -> Ansible -> Docker -> Kubernetes.

## Endpoints
- GET  /         - service status
- GET  /health   - health check
- GET  /quote    - random quote
- GET  /quotes   - list all quotes
- POST /quotes   - add a quote (JSON: {"quote": "..."})

## Run locally
    pip install -r requirements.txt
    python app.py
