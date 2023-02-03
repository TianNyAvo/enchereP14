package s5.cloud.enchere.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FirebaseMessagingService {

     private final FirebaseMessaging firebaseMessaging;

     public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
          this.firebaseMessaging = firebaseMessaging;
     }

     public String sendNotification(String titre, String contenu, String token)
               throws FirebaseMessagingException {

          Notification notification = Notification
                    .builder()
                    .setTitle(titre)
                    .setBody(contenu)
                    .setImage(getClass().getClassLoader().getResource(".").getFile() + "notif.png")
                    .build();

          Message message = Message
                    .builder()
                    .setToken(token)
                    .setNotification(notification)
                    .build();

          return firebaseMessaging.send(message);
     }

     public static void main(String[] args) {
          System.out.println(
                    FirebaseMessagingService.class.getClassLoader().getResource(".").getFile() + "notif.png");
     }

}
