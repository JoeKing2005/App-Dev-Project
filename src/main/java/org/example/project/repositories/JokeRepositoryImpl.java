//package org.example.project.repositories;
//
//import org.example.project.entities.Joke;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.simple.JdbcClient;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class JokeRepositoryImpl implements JokeRepository {
//
//    private final JdbcClient jdbcClient;
//
//    private final RowMapper<Joke> jokeRowMapper = BeanPropertyRowMapper.newInstance(Joke.class);
//
//    public JokeRepositoryImpl(JdbcClient jdbcClient) {this.jdbcClient = jdbcClient; }
//
//    @Override
//    public List<Joke> findAll() {
//        return jdbcClient
//                .sql("select * from jokes").query(jokeRowMapper).list();
//    }
//
//    public Optional<Joke> findById(int id) {
//        return jdbcClient
//                .sql("Select * FROM jokes where id = :id")
//                .param("id", id)
//                .query(jokeRowMapper)
//                .optional();
//    }
//
//
//    @Override
//    public List<Joke> findByCategory(String category) {
//        return jdbcClient.sql("""
//                SELECT id, setup, punchline, category
//                FROM jokes
//                WHERE category = :category
//        """).param("category", category).query(jokeRowMapper).list();
//    }
//
//    @Override
//    public List<Joke> findByAge(int age_appropriateness) {
//        return jdbcClient.sql("""
//            SELECT *
//            FROM jokes
//            WHERE age_appropriateness = :age_appropriateness
//        """).param("age_appropriateness", age_appropriateness).query(jokeRowMapper).list();
//    }
//
//    @Override
//    public List<Joke> findByRating(int rating) {
//        return jdbcClient.sql("""
//        SELECT *
//        FROM jokes
//        WHERE rating = :rating
//""").param("rating", rating).query(jokeRowMapper).list();
//    }
//
//    public Optional<Double> average_rating() {
//        return jdbcClient.sql("""
//        SELECT AVG(rating) FROM jokes
//""").query(Double.class).optional();
//    }
//
//    @Override
//    public boolean deleteById(int id) {
//
//        int rowsAffected = jdbcClient.sql("""
//                DELETE FROM jokes
//                WHERE id = :id
//""").param("id", id).update();
//
//        return rowsAffected > 0;
//    }
//
//    @Override
//    public boolean updatePunchline(int id, String punchline) {
//
//        int rowsAffected = jdbcClient.sql("""
//            UPDATE jokes
//            SET punchline = :punchline
//            WHERE id = :id
//        """).param("punchline", punchline).param("id", id).update();
//
//        return rowsAffected > 0;
//    }
//
//    @Override
//    public Joke save(Joke joke) {
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcClient.sql("""
//                INSERT INTO jokes (setup, punchline, category)
//                VALUES (:setup, :punchline, :category)
//""").param("setup", joke.getSetup()).param("punchline", joke.getPunchline()).param("category", joke.getCategory()).update(keyHolder, "id");
//
//        Number generatedId = keyHolder.getKey();
//
//        if (generatedId == null) {
//            throw new IllegalStateException("Database did not generate an id");
//        }
//
//        joke.setId(generatedId.intValue());
//
//        return joke;
//    }
//}
