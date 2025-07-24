import string
from locust import HttpUser, task, between
import uuid
import random
from datetime import datetime, timezone

def generate_random_seat_id():
    letter = random.choice(string.ascii_uppercase)       # A~Z
    number = random.randint(1, 999)                     # 1~999
    return f"{letter}{number:03}"

class TicketReserveUser(HttpUser):
    wait_time = between(0.1, 0.5)  # 빠르게 반복 테스트할 수 있게 설정

    @task
    def reserve_seat(self):
        user_id = str(uuid.uuid4())
        seat_id = generate_random_seat_id() # 충돌 줄이기 위해 랜덤 수 증가

        payload = {
            "userId": user_id,
            "seatId": seat_id,
            "timestamp": datetime.now(timezone.utc).isoformat()
        }

        # self.client.post("/reserve", json=payload)
        with self.client.post("/reserve", json=payload, catch_response=True) as response:
            if response.status_code == 200:
                response.success()
                # self.stop()  # 이 유저는 더 이상 테스트 안 함
            else:
                response.failure("예약 실패")


#  docker run --rm -p 8089:8089 \
#   -v $(pwd)/locust:/mnt/locust \
#   -w /mnt/locust \
#   locustio/locust:2.37.14 \
#   -f locustfile.py \
#   --host http://host.docker.internal:8081