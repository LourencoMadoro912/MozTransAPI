CREATE TABLE ticket(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
assento VARCHAR(10),
price DOUBLE,
trip_id BIGINT,
CONSTRAINT fk_ticket_trip FOREIGN KEY(trip_id) REFERENCES trip(id),
custumer_id BIGINT,
CONSTRAINT fk_ticket_custumer FOREIGN KEY(custumer_id) REFERENCES  custumer(id)
)