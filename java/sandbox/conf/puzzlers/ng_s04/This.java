package sandbox.conf.puzzlers.ng_s04;

 class This {
     final String thіs;
     This() {
         this.thіs = This.this.thіs + "this";
     }
     public static void main(String[] args) {
         System.out.println(new
                 This().thіs.length());
     }
 }
