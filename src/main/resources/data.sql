-- ============================================
-- SEAT BOOKING SYSTEM - DATABASE SETUP
-- ============================================

-- --------------------------------------------
-- 1. Insert Demo Shows
-- --------------------------------------------

INSERT INTO movie_show (show_name)
VALUES
('Avengers - 7 PM Show'),
('Batman - 9 PM Show');


-- --------------------------------------------
-- 3. Insert Demo Seats
-- --------------------------------------------

INSERT INTO seat (show_id, seat_number, seat_status, version)
VALUES
(1, 'A1', 'AVAILABLE', 0),
(1, 'A2', 'AVAILABLE', 0),
(1, 'A3', 'AVAILABLE', 0),
(1, 'A4', 'AVAILABLE', 0),
(1, 'A5', 'AVAILABLE', 0),

(2, 'B1', 'AVAILABLE', 0),
(2, 'B2', 'AVAILABLE', 0),
(2, 'B3', 'AVAILABLE', 0),
(2, 'B4', 'AVAILABLE', 0),
(2, 'B5', 'AVAILABLE', 0);