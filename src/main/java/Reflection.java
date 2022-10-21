import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Reflection {
    public static void main(String[] args) {
        Scanner input = null;
        File file = new File("src/data.txt");

        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Object> objectsList = new ArrayList<>();

        Object instance;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] splittedLine = line.split("\\|");

            try {
                Class<?> obj = Class.forName(splittedLine[1]);
                Method[] metodos = obj.getDeclaredMethods();

                try {
                    instance = obj.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                int count = 2;
                for (Method metodo : metodos) {
                    if (metodo.toString().contains("set")) {
                        Object donoDoPet = encontraDonoDoPetPorDocumento(objectsList, splittedLine[count]);
                        if (Objects.nonNull(donoDoPet)) {
                            metodo.invoke(instance, donoDoPet);
                            if (count < splittedLine.length - 1) {
                                count++;
                            }
                            continue;
                        }
                        metodo.invoke(instance, splittedLine[count]);
                        if (count < splittedLine.length - 1) {
                            count++;
                        }
                    } else if (metodo.toString().contains("get")) {
                        try {
                            metodo.invoke(instance);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

//            if (splittedLine[1].equals("Pessoa")) {
//                try {
//                    Method methodSetDocumento = Pessoa.class.getDeclaredMethod("setDocumento", String.class);
//                    Method methodSetNome = Pessoa.class.getDeclaredMethod("setNome", String.class);
//                    Method methodSetEndereco = Pessoa.class.getDeclaredMethod("setDocumento", String.class);
//
//                    methodSetDocumento.invoke(pessoa, splittedLine[2]);
//                    methodSetNome.invoke(pessoa,splittedLine[3]);
//                    methodSetEndereco.invoke(pessoa, splittedLine[4]);
//
//                    pessoaList.add(pessoa);
//
//                    Method methodGetDocumento = Pessoa.class.getDeclaredMethod("getDocumento");
//                    Method methodGetNome = Pessoa.class.getDeclaredMethod("getNome");
//                    Method methodGetEndereco = Pessoa.class.getDeclaredMethod("getEndereco");

//                    System.out.println("### Pessoa");
//                    System.out.println("Documento: " + methodGetDocumento.invoke(pessoa));
//                    System.out.println("Nome: " + methodGetNome.invoke(pessoa));
//                    System.out.println("Endereço: " + methodGetEndereco.invoke(pessoa));
//                    System.out.println("");

//                    System.out.println();
//                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                try {
//                    Method methodSetNomePet = Pet.class.getDeclaredMethod("setNome", String.class);
//                    Method methodSetProprietario = Pet.class.getDeclaredMethod("setProprietario", Pessoa.class);
//
//                    methodSetNomePet.invoke(pet, splittedLine[2]);
//                    methodSetProprietario.invoke(pet, pessoaList.get(count));
//                    count++;
//
//                    Method methodGetNomePet = Pet.class.getDeclaredMethod("getNome");
//                    Method methodGetProprietario = Pet.class.getDeclaredMethod("getProprietario");

//                    System.out.println("### Pet");
//                    System.out.println("Nome: " + methodGetNomePet.invoke(pet));
//                    System.out.println("Nome do proprietário do pet: " + methodGetProprietario.invoke(pet));
//                    System.out.println("");
//                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            objectsList.add(instance);
        }
        for (Object object : objectsList) {
            System.out.println(object);
        }
    }

//    public static boolean containsDocument(Object object, String document) {
//        try {
//            Method method=  object.getClass().getDeclaredMethod("getDocumento");
//             return method.invoke(object).equals(document);
//        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static Object encontraDonoDoPetPorDocumento(final List<Object> list, final String documento) {
        Object result = null;
        for (Object o : list) {
            if (!o.getClass().equals(Pessoa.class)) {
                continue;
            } else {
                Method metodo = null;
                try {
                    metodo = o.getClass().getDeclaredMethod("getDocumento");
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (metodo.invoke(o).equals(documento)) {
                        result = o;
                        return result;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

}


//7|Inventario|Toto-Filete-Fefo|3
//3|Pessoa|222222|Melio|Rua X
//4|Pet|22222|Filete
//5|Pessoa|33333|Teló|Rua F
//6|Pet|33333|Fefo