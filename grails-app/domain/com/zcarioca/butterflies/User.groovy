package com.zcarioca.butterflies

/**
 * The Users of the system.
 * 
 * 
 * @author zcarioca
 */
class User {

   String username
   String password
   Profile profile
   
   String toString() {
      return "User ${username}"
   }

   static constraints = {
      username(size:3..20, unique:true)
      password(blank:false, nullable: false)
      profile(nullable: false)
   }
}
