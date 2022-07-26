import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes

        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClientHttp();
        String json = http.buscaDados(url);        

        // exibir e manipular os dados        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) { // para percorrer apenas o top 10

            Conteudo conteudo = conteudos.get(i);
                    
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png"; // acrescentado a saida para especificar o diretorio.
 
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
