rem Start 1 network node, then terminate it
start java Main NetworkNode -ident 1 -tcpport 9000 A:1
timeout 1 > NUL
java Main NetworkClient -gateway localhost:9000 terminate
