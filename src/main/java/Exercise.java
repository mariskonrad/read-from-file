import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise {
    public static void main(String[] args) throws IOException {
        Scanner input = null;
        File file = new File("src/data.txt");

        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Pessoa> pessoaList = new ArrayList<>();
        List<Pet> petList = new ArrayList<>();

        int count = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] splittedLine = line.split("\\|");

            Pessoa pessoa = new Pessoa();
            Pet pet = new Pet();
            if (splittedLine[1].equals("Pessoa")) {
                pessoa.setDocumento(splittedLine[2]);
                pessoa.setNome(splittedLine[3]);
                pessoa.setEndereco(splittedLine[4]);
                pessoaList.add(pessoa);

                System.out.println("### Pessoa");
                System.out.println("Documento: " + pessoa.getDocumento());
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("Endereço: " + pessoa.getEndereco());
                System.out.println("");
            } else {
                pet.setNome(splittedLine[2]);
                pet.setProprietario(pessoaList.get(count));
                petList.add(pet);
                count++;

                System.out.println("### Pet");
                System.out.println("Nome: " + pet.getNome());
                System.out.println("Documento do proprietário: " + pet.getProprietario().getDocumento());
                System.out.println("Nome do proprietário: " + pet.getProprietario().getNome());
                System.out.println("Endereço do proprietário: " + pet.getProprietario().getEndereco());
                System.out.println("");
            }
        }
    }
}
