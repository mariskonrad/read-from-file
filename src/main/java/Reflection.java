import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] splittedLine = line.split("\\|");

            try {
                Class<?> obj = Class.forName(splittedLine[1]);
                Method[] metodos = obj.getDeclaredMethods();

                Object instance;
                try {
                    instance = obj.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                int count = 2;
                for (Method metodo : metodos) {
                    if (metodo.toString().contains("set")) {
                        if (splittedLine[1].equals("Pet")) {
                            System.out.println("aqui");
                        }
                        for (Object object : objectsList)
                            if (containsDocument(object, splittedLine[count])) {
                                metodo.invoke(instance, object);
                            } else break;
                        metodo.invoke(instance, splittedLine[count]);
                        if (count < splittedLine.length - 1) {
                            count++;
                        }
                    } else if (metodo.toString().contains("get")) {
                        try {
                            System.out.println(metodo.invoke(instance));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                objectsList.add(instance);
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
        }
    }

    public static boolean containsDocument(Object object, String document) {
        try {
            Method method=  object.getClass().getDeclaredMethod("getDocumento");
             return method.invoke(object).equals(document);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void containsDocument(final List<Object> list, final String documento){
//        list.stream().anyMatch(o -> {
//            try {
//                Method metodo = o.getClass().getDeclaredMethod("getDocumento");
//                    metodo.invoke()
//
//            } catch (NoSuchMethodException e) {
//                throw new RuntimeException(e);
//            }
//            return false;
//        });
//    }


}


//7|Inventario|Toto-Filete-Fefo|3
//3|Pessoa|222222|Melio|Rua X
//        4|Pet|22222|Filete
//        5|Pessoa|33333|Teló|Rua F
//        6|Pet|33333|Fefo