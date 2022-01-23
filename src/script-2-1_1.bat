rem Start 2 network nodes, then allocate the resources using the first one as a gateway
start java Main NetworkNode -ident 1 -tcpport 9000 A:1 
timeout 1 > NUL
start java Main NetworkNode -ident 2 -tcpport 9001 -gateway localhost:9000 B:1 
timeout 1 > NUL
java Main NetworkClient -ident 1 -gateway localhost:9000 A:1 B:1
java Main NetworkClient -gateway localhost:9000 terminate
