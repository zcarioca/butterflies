package com.zcarioca.butterflies

/**
 * A user profile.
 * 
 * 
 * @author zcarioca
 */
class Profile {

   String firstName
   String lastName
   String email
   String homepage
   String bio
   Address address
   byte[] photo
   
   static belongsTo = User
   
   String toString() {
      return "Profile for ${email}"
   }

   static constraints = {
      firstName(nullable: true)
      lastName(nullable: true)
      email(email: true, blank: false)
      homepage(url: true, nullable: true)
      bio(maxSize: 1000, nullable: true)
      address(nullable: true)
      photo(nullable: true)
   }
}
