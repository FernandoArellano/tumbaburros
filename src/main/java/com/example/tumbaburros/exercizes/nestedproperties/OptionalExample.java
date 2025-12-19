package com.example.tumbaburros.exercizes.nestedproperties;


import java.util.Optional;

class User { Profile profile; }
class Profile { String email; }


    public class OptionalExample {

        public static String getEmailOrDefault(User user){
            return Optional.ofNullable(user)
                    .map(u -> u.profile)
                    .map( p -> p.email)
                    .filter(s -> s.contains("@"))
                    .orElse("unknown");
        }

        public static void main(String[] args) {
            Profile profile1 = new Profile();
            profile1.email = "fer";

            Profile profile2 = new Profile();
            profile2.email = "fer@hotmail.com";

            User user1 = new User();
            user1.profile = profile1;

            User user2= new User();
            user2.profile = profile2;

            System.out.println(OptionalExample.getEmailOrDefault(user1));
            System.out.println(OptionalExample.getEmailOrDefault(user2));
        }

}
