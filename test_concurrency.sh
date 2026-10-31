#!/bin/bash
echo "1. Registering 5 Players..."
for i in {1..5}; do curl -s -X POST "http://localhost:8080/api/players?name=Player$i" > /dev/null; done

echo "2. Starting Game..."
curl -s -X POST http://localhost:8080/api/game/start > /dev/null

echo "3. Simulating Concurrent Collection of Treasure ID 1..."
# The '&' runs them in parallel at the exact same time
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=1" &
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=2" &
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=3" &
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=4" &
curl -s -X POST "http://localhost:8080/api/game/collect/1?playerId=5" &
wait

echo -e "\n\n4. Leaderboard Check (Only one player should have points):"
curl -s http://localhost:8080/api/leaderboard
