package com.zcarioca.butterflies

class FamilyController {
   
   def index = {
      redirect(action: 'list')
   }

   def list = {
      def parentFamilies = Family.findAllBySuperFamilyIsNull();
      
      [ families : parentFamilies ]
   }
   
   def show = {
      def fam = Family.get(params.id);
      def species = Lepidoptera.findAllByFamily(fam)
      [ family : fam, species: species ]
   }
}
