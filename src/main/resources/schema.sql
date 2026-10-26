CREATE TABLE jokes (
    id int PRIMARY KEY AUTO_INCREMENT,
    setup VARCHAR(255) NOT NULL,
    punchline VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    age_appropriateness int NOT NULL,
    rating int,
    language VARCHAR(10) DEFAULT 'en' NOT NULL);