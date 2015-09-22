package chapter1;

public class Ex11 {
    //feel free to play around
    public interface I {
        //void f();
        default void f() { System.out.println("default I.f()"); }
        //static void f() { System.out.println("static I.f()"); }
    }
    public interface J {
        //void f();
        //default void f() { System.out.println("default J.f()"); }
        static void f() { System.out.println("static J.f()"); }
    }
    public abstract static class S {
        //public abstract void f();
        public void f() { System.out.println("S.f()"); }
        //public static void f() { System.out.println("static S.f()"); }
    }

    public static class C implements I, J {

    }

    public static class C2 extends S implements I {

    }

    public static void main(String[] args) {
        new C().f();
        new C2().f();
    }
}
