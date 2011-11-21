package com.zcarioca.butterflies

import grails.test.*

class UserIntegrationTests extends GroovyTestCase {

   void testSave() {
      def user = new User(username: 'joe', password: 'secret', profile: new Profile(email: "test@test.com"))
      assertNotNull user.save()
      assertNotNull user.id

      def foundUser = User.get(user.id)
      assertEquals 'joe', foundUser.username
   }

   void testUpdate() {
      def user = new User(username: 'joe', password: 'secret', profile: new Profile(email: "test@test.com"))
      assertNotNull user.save()
      assertNotNull user.id

      def foundUser = User.get(user.id)
      assertEquals 'joe', foundUser.username
      foundUser.password = 'sesame'
      assertNotNull foundUser.save()

      def editUser = User.get(user.id)
      assertEquals 'sesame', editUser.password
   }

   void testDelete() {
      def user = new User(username: 'joe', password: 'secret', profile: new Profile(email: "test@test.com"))
      assertNotNull user.save()
      assertNotNull user.id

      def foundUser = User.get(user.id)
      assertEquals 'joe', foundUser.username
      foundUser.delete()

      assertFalse User.exists(foundUser.id)
   }

   void testBadUsername() {
      def user = new User(username: 'hi', profile: new Profile(email: "test@test.com"))
      assertFalse user.validate()
      assertTrue user.hasErrors()

      def errors = user.errors
      assertEquals 'size.toosmall', errors.getFieldError('username').code
      assertEquals 'hi', errors.getFieldError('username').rejectedValue
      assertEquals 'nullable', errors.getFieldError('password').code
   }

   void testBadSaveCorrected() {
      def user = new User(username: 'hi', profile: new Profile(email: "test@test.com"))
      assertFalse user.validate()
      assertTrue user.hasErrors()

      def errors = user.errors
      assertEquals 'size.toosmall', errors.getFieldError('username').code
      assertEquals 'hi', errors.getFieldError('username').rejectedValue
      assertEquals 'nullable', errors.getFieldError('password').code

      user.password = 'secret'
      user.username = 'joe'
      assertNotNull user.save()
   }
}
