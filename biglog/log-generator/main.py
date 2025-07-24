from kafka import KafkaProducer
import json
import time
import random
from datetime import datetime, UTC

producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

log_levels = ['INFO', 'WARN', 'ERROR', 'DEBUG']
endpoints = ['/login', '/logout', '/purchase', '/search', '/view']

while True:
    log = {
        'timestamp': datetime.now(UTC).isoformat(),
        'level': random.choice(log_levels),
        'endpoint': random.choice(endpoints),
        'user_id': random.randint(1, 10000),
        'message': 'sample log message'
    }
    producer.send('log-topic', log)
    time.sleep(0.01)  # 100 logs/sec → 줄이면 TPS 증가
