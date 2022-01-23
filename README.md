# Resource-reservation-system

## Commands

| Command | Description |
| --- | --- |
| NetworkClient | Start the network client to connect to the server |
| NetworkNode | Start the server/node |

## Arguments for client

| Argument | Description |
| --- | --- |
| -gateway | Format: "ip:port" - IP and Port from node |
| -ident | Identifier for client (numeric) |
| terminate | Terminates the client after connecting to the server |
| Ressourcename:Ressourcevalue(numeric) | - The value that the client need |

## Arguments for server

| Argument | Description |
| --- | --- |
| -tcpport | Set the tcp port of the server |
| -ident | Identifier for server (numeric) |
| Ressourcename:Ressourcevalue(numeric) | - The ressources of the server |

## Responses from server

| Response | Description |
| --- | --- |
| TERMINATED | The server/client connection work and the client will be terminated |
| ALLOCATED name:value[] | Succesful connected to server. Ressources for client reserved |
| FAILED | Server dont have enough resources |

