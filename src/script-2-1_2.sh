#!/bin/bash

# Start 2 network nodes, then allocate the resources using the second one as a gateway
java Main NetworkNode -ident 1 -tcpport 9000 A:1 &
sleep 1
java Main NetworkNode -ident 2 -tcpport 9001 -gateway localhost:9000 B:1 &
sleep 1
java Main NetworkClient -ident 1 -gateway localhost:9001 A:1 B:1
java Main NetworkClient -gateway localhost:9001 terminate
