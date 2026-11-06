CREATE TABLE app_users (
   id INT PRIMARY KEY AUTO_INCREMENT,
   email VARCHAR(255) NOT NULL UNIQUE,
   firstname VARCHAR(255),
   lastname VARCHAR(255)
);

CREATE TABLE jokes (
    id int PRIMARY KEY AUTO_INCREMENT,
    setup VARCHAR(255) NOT NULL,
    punchline VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    age_appropriateness int NOT NULL,
    rating int,
    language VARCHAR(10) DEFAULT 'en' NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT joke_user
                   FOREIGN KEY (user_id) REFERENCES app_users(id));
