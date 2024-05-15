package org.example.practice16.repositories;

import lombok.AllArgsConstructor;
import org.example.practice16.entities.Feedback;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class FeedbackRepository {
    private final DataSource ds;
    public List<Feedback> getFeedbacks() throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from feedbacks");
        ResultSet rs = ps.executeQuery();
        List<Feedback> feedbacks = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String description = rs.getString(3);
            int product_id = rs.getInt(4);
            feedbacks.add(new Feedback(id, name, description, product_id));
        }
        return feedbacks;
    }

    public Feedback addFeedback(Feedback feedback) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into subjects (id, name, description, product_id) values (?, ?, ?, ?)", new String[] {"id"});
        ps.setInt(1, feedback.getId());
        ps.setString(2, feedback.getName());
        ps.setString(3, feedback.getDescription());
        ps.setInt(4, feedback.getProduct_id());
        ps.executeUpdate();
        return feedback;
    }

    public boolean deleteFeedback(int id) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from feedbacks where id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}
