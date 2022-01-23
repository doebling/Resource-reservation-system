#!/bin/bash

# Start 1 network node, then allocate the resources using it
java Main NetworkNode -ident 1 -tcpport 9000 A:1 B:1 &
sleep 1
java Main NetworkClient -ident 1 -gateway localhost:9000 A:1
java Main NetworkClient -ident 2 -gateway localhost:9000 B:1
java Main NetworkClient -gateway localhost:9000 terminate
