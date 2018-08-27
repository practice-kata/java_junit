import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Enclosed.class)
public class UserSignatureTest {

    @RunWith(Theories.class)
    public static class ユーザの妥当性検証 {
        @DataPoints
        public static Fixture[] patterns = {
                new Fixture("password", true),
                new Fixture("passwor", false),
        };

        @Theory
        public void パスワード閾値テスト(Fixture pattern) {
            //given
            User user = new UserBuilder().setPassword(pattern.password).build();
            //when
            boolean actual = user.validate();
            //then
            assertEquals(pattern.expected, actual);
        }

        private static class Fixture {
            private String password;
            private boolean expected;

            public Fixture(String password, boolean expected) {
                this.password = password;
                this.expected = expected;
            }
        }

        private class UserBuilder {

            private String password;

            public UserBuilder setPassword(String password) {
                this.password = password;
                return this;
            }

            public User build() {
                return new User("", "", password);
            }
        }
    }

    @RunWith(Theories.class)
    public static class 署名の取得 {

        @DataPoints
        public static UserData[] patterns(){
            return new UserData[] {
                    new UserData().setExpected("tanaka: PD OK"),
                    new UserData().setName("yamada").setExpected("yamada: PD OK"),
                    new UserData().setCompanyName("MJ").setExpected("tanaka: MJ OK"),
                    new UserData().setPassord("passwor").setExpected("tanaka: PD NG"),
                    new UserData().setCompanyName("").setExpected("tanaka OK"),
            };
        }

        @Theory
        public void 署名取得パターン(UserData pattern){
            //given
            User user = new User(pattern.name, pattern.companyName, pattern.password);
            //when
            String actual = user.signature();
            //then
            assertEquals(pattern.expected, actual);
        }

        private static class UserData {
            private String name = "tanaka";
            private String companyName = "PD";
            private String password = "password";
            private String expected;

            public UserData setName(String name) {
                this.name = name;
                return this;
            }

            public UserData setCompanyName(String companyName) {
                this.companyName = companyName;
                return this;
            }

            public UserData setPassord(String s) {
                this.password = s;
                return this;
            }

            public UserData setExpected(String s) {
                this.expected = s;
                return this;
            }


        }


    }
}

