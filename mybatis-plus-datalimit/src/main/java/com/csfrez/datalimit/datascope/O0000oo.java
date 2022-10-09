package com.csfrez.datalimit.datascope;

import com.csfrez.datalimit.annotation.JsonBind;
import com.csfrez.datalimit.databind.IJsonBindStrategy;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.lang.Nullable;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

public class O0000oo {
    public O0000oo() {
    }

    public static String O000O0OO(String var0) {
        int var1 = var0.lastIndexOf(".");
        return var1 > 0 ? var0.substring(0, var1) : null;
    }

/*
    public static void O000000o(String var0, String var1, Consumer<Boolean> var2) {
        try {
            if (O00000Oo((CharSequence)var0) || O00000Oo((CharSequence)var1)) {
                var2.accept(true);
            }

            var2.accept(!O000000o(var0, var1, O000O0o.O000OoOo()));
        } catch (Exception var4) {
            var2.accept(true);
        }

    }
*/

/*
    public static boolean O000000o(String var0, String var1, PublicKey var2) throws Exception {
        return O0000Oo.O000000o(var0, var1, O000O0o.O00000o0(var1, var2).split(O00000oo.O000O0oO()));
    }
*/

    public static boolean O000000o(CharSequence... var0) {
        return !O00000Oo(var0);
    }

    public static boolean O00000Oo(CharSequence... var0) {
        if (O000000o((Object[])var0)) {
            return false;
        } else {
            CharSequence[] var1 = var0;
            int var2 = var0.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence var4 = var1[var3];
                if (O00000Oo(var4)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean O000000o(Collection<?> var0) {
        return var0 == null || var0.isEmpty();
    }

    public static boolean O00000Oo(Collection<?> var0) {
        return !O000000o(var0);
    }

    public static boolean O000000o(Object[] var0) {
        return O00000o0((Object)var0) == 0;
    }

    public static int O00000o0(Object var0) {
        return var0 == null ? 0 : Array.getLength(var0);
    }

    public static boolean O000000o(@Nullable Map<?, ?> var0) {
        return var0 == null || var0.isEmpty();
    }

    public static boolean O000000o(CharSequence var0) {
        return var0 == null || var0.length() == 0;
    }

    public static boolean O00000Oo(CharSequence var0) {
        int var1 = O00000o0(var0);
        if (var1 == 0) {
            return true;
        } else {
            for(int var2 = 0; var2 < var1; ++var2) {
                if (!Character.isWhitespace(var0.charAt(var2))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static int O00000o0(CharSequence var0) {
        return var0 == null ? 0 : var0.length();
    }

    public static String O000000o(String var0, int var1) {
        if (var0 == null) {
            return null;
        } else if (var1 < 0) {
            return "";
        } else {
            return var0.length() <= var1 ? var0 : var0.substring(0, var1);
        }
    }

    public static String O000000o(String var0, int var1, String var2) {
        if (var0 == null) {
            return null;
        } else {
            if (O000000o((CharSequence)var2)) {
                var2 = " ";
            }

            int var3 = var2.length();
            int var4 = var0.length();
            int var5 = var1 - var4;
            if (var5 <= 0) {
                return var0;
            } else if (var3 == 1 && var5 <= 8192) {
                return O000000o(var0, var1, var2.charAt(0));
            } else if (var5 == var3) {
                return var2.concat(var0);
            } else if (var5 < var3) {
                return var2.substring(0, var5).concat(var0);
            } else {
                char[] var6 = new char[var5];
                char[] var7 = var2.toCharArray();

                for(int var8 = 0; var8 < var5; ++var8) {
                    var6[var8] = var7[var8 % var3];
                }

                return (new String(var6)).concat(var0);
            }
        }
    }

    public static String O000000o(String var0, int var1, char var2) {
        if (var0 == null) {
            return null;
        } else {
            int var3 = var1 - var0.length();
            if (var3 <= 0) {
                return var0;
            } else {
                return var3 > 8192 ? O000000o(var0, var1, String.valueOf(var2)) : O00000Oo(var2, var3).concat(var0);
            }
        }
    }

    public static String O00000Oo(String var0, int var1, String var2) {
        if (var0 == null) {
            return null;
        } else {
            if (O000000o((CharSequence)var2)) {
                var2 = " ";
            }

            int var3 = var2.length();
            int var4 = var0.length();
            int var5 = var1 - var4;
            if (var5 <= 0) {
                return var0;
            } else if (var3 == 1 && var5 <= 8192) {
                return O00000Oo(var0, var1, var2.charAt(0));
            } else if (var5 == var3) {
                return var0.concat(var2);
            } else if (var5 < var3) {
                return var0.concat(var2.substring(0, var5));
            } else {
                char[] var6 = new char[var5];
                char[] var7 = var2.toCharArray();

                for(int var8 = 0; var8 < var5; ++var8) {
                    var6[var8] = var7[var8 % var3];
                }

                return var0.concat(new String(var6));
            }
        }
    }

    public static String O00000Oo(String var0, int var1, char var2) {
        if (var0 == null) {
            return null;
        } else {
            int var3 = var1 - var0.length();
            if (var3 <= 0) {
                return var0;
            } else {
                return var3 > 8192 ? O00000Oo(var0, var1, String.valueOf(var2)) : var0.concat(O00000Oo(var2, var3));
            }
        }
    }

    public static String O00000Oo(char var0, int var1) {
        if (var1 <= 0) {
            return "";
        } else {
            char[] var2 = new char[var1];

            for(int var3 = var1 - 1; var3 >= 0; --var3) {
                var2[var3] = var0;
            }

            return new String(var2);
        }
    }

    public static String O00000Oo(String var0, int var1) {
        if (var0 == null) {
            return null;
        } else if (var1 < 0) {
            return "";
        } else {
            return var0.length() <= var1 ? var0 : var0.substring(var0.length() - var1);
        }
    }

    public static String O00000o0(String var0, int var1) {
        return O00000Oo(var0, var1, ' ');
    }

    public static int O00000Oo(String var0, String var1) {
        return O000000o((CharSequence)var0) ? -1 : var0.indexOf(var1);
    }

    public static String O00000o0(String var0, String var1) {
        if (!O000000o((CharSequence)var0) && !O000000o((CharSequence)var1)) {
            return var0.startsWith(var1) ? var0.substring(var1.length()) : var0;
        } else {
            return var0;
        }
    }

    public static String O000000o(String var0, int var1, int var2) {
        if (var0 == null) {
            return null;
        } else if (var2 >= 0 && var1 <= var0.length()) {
            if (var1 < 0) {
                var1 = 0;
            }

            return var0.length() <= var1 + var2 ? var0.substring(var1) : var0.substring(var1, var1 + var2);
        } else {
            return "";
        }
    }

/*
    private static String O00000o0(byte[] var0) {
        StringBuilder var1 = new StringBuilder(var0.length * 2);

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1.append(O0000oOo.O00O00o0[(var0[var2] & 240) >>> 4]);
            var1.append(O0000oOo.O00O00o0[var0[var2] & 15]);
        }

        return var1.toString();
    }
*/

/*
    public static String O000O0Oo(String var0) throws NoSuchAlgorithmException {
        MessageDigest var1 = MessageDigest.getInstance("MD5");
        var1.update(var0.getBytes());
        return O00000o0(var1.digest());
    }
*/

/*
    public static String O00oOoOo(String var0) throws NoSuchAlgorithmException {
        return O000O0Oo(var0).substring(8, 24);
    }
*/

    public static void O000000o(Object var0, JsonGenerator var1, IJsonBindStrategy var2, JsonBind var3) throws Exception {
        O000000o(O00000o(var0), var1);
        O000000o(var2.handle(var3.value(), var0), var1);
    }

    public static void O000000o(Map<String, Object> var0, JsonGenerator var1) throws Exception {
        Set var2 = var0.entrySet();
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            Map.Entry var4 = (Map.Entry)var3.next();
            var1.writeObjectField((String)var4.getKey(), var4.getValue());
        }

    }

    public static Map<String, Object> O00000o(Object var0) throws Exception {
        HashMap var1 = new HashMap(16);
        BeanInfo var2 = Introspector.getBeanInfo(var0.getClass());
        PropertyDescriptor[] var3 = var2.getPropertyDescriptors();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PropertyDescriptor var6 = var3[var5];
            Method var7 = var6.getReadMethod();
            if (var7 != null) {
                var1.put(var6.getName(), var7.invoke(var0));
            }
        }

        var1.remove("class");
        return var1;
    }
}