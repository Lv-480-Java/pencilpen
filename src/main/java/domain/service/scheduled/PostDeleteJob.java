package domain.service.scheduled;

import dao.implementation.PostDao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PostDeleteJob {
    private PostDao dostDao = new PostDao();

    private String getDeletingDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Date date = new Date();

        LocalDateTime dateTime = date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        dateTime = dateTime.minusMonths(1);
        return formatter.format(dateTime);
    }

    void deletePosts() {
        String date = getDeletingDate();
        boolean isActivePost = false;
        dostDao.deletePostBeforeDate(date, isActivePost);
    }
}
