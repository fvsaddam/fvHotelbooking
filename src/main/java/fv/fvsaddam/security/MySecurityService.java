package fv.fvsaddam.security;

import fv.fvsaddam.model.Booking;
import fv.fvsaddam.model.Comment;
import fv.fvsaddam.model.CustomUserDetail;
import fv.fvsaddam.model.Hotel;
import fv.fvsaddam.model.User;
import fv.fvsaddam.repository.BookingRepository;
import fv.fvsaddam.repository.CommentRepository;
import fv.fvsaddam.repository.HotelRepository;
import fv.fvsaddam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fvsaddam - fvmonster.corp@gmail.com
 */
@Component("mySecurityService")
public class MySecurityService {

    @Autowired
    HotelRepository hotels;

    @Autowired
    UserRepository users;

    @Autowired
    CommentRepository comments;

    @Autowired
    BookingRepository bookings;

    public boolean canEditHotel(long hotel_id, CustomUserDetail user) {
        Hotel hotel = hotels.findOne(hotel_id);
        return hotel != null && hotel.getManager() != null && user.getUser().getId() == hotel.getManager().getId();
    }

    public boolean canEditHotel(long hotel_id, String s) {
        return false;
    }

    public boolean canEditUser(long user_id, CustomUserDetail user) {
        User userTmp = users.findOne(user_id);
        return userTmp != null && user.getUser() != null && user.getUser().getId() == userTmp.getId();
    }

    public boolean canEditComment(long comment_id, CustomUserDetail user) {
        Comment comment = comments.findOne(comment_id);
        return comment != null && user != null && comment.getUser().getId() == user.getUser().getId();

    }

    public boolean canReplyToComment(long id, long comment_id, CustomUserDetail user) {
        Hotel hotel = hotels.findOne(id);
        Comment comment = comments.findOne(comment_id);
        return comment != null && user != null && comment.getStatus()
                && !comment.getIsAnswer() && hotel != null && hotel.getManager().getId() == user.getUser().getId();
    }

    public boolean canApproveBooking(long booking_id, CustomUserDetail user) {
        Booking booking = bookings.findOne(booking_id);
        return booking != null && user != null && booking.getHotel().getManager().getId() == user.getUser().getId();
    }

    public boolean canRemoveBooking(long booking_id, CustomUserDetail user) {
        Booking booking = bookings.findOne(booking_id);
        return booking != null && user != null
                && (booking.getHotel().getManager().getId() == user.getUser().getId()
                || booking.getUser().getId() == user.getUser().getId());

    }
}
