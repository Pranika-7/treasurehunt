#!/bin/bash

echo "=== 1. REGISTERING PLAYERS ==="
echo "Registering Pranika..."
curl -s -X POST "http://localhost:8080/api/players?name=Pranika"
echo -e "\n"

echo "Registering PlayerTwo..."
curl -s -X POST "http://localhost:8080/api/players?name=PlayerTwo"
echo -e "\n"

echo "=== 2. STARTING GAME & SPAWNING TREASURES ==="
curl -s -X POST "http://localhost:8080/api/game/start"
echo -e "\n"

echo "=== 3. VIEWING THE MAP ==="
curl -s "http://localhost:8080/api/game/map"
echo -e "\n"

echo "=== 4. PRANIKA COLLECTS TREASURE ID 1 ==="
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=1"
echo -e "\n"

echo "=== 5. PLAYERTWO TRIES TO COLLECT THE SAME TREASURE ID 1 (SHOULD BE REJECTED) ==="
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=2"
echo -e "\n"

echo "=== 6. PLAYERTWO COLLECTS AVAILABLE TREASURE ID 2 ==="
curl -s -X POST "http://localhost:8080/api/game/collect/2?playerId=2"
echo -e "\n"

echo "=== 7. FINAL LEADERBOARD RANKINGS ==="
curl -s "http://localhost:8080/api/leaderboard"
echo -e "\n"
