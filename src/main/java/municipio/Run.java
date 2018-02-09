package municipio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Run {

	public static void main(String[] args) {

		List<Municipio> municipios = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("municipio-list"))) {

			String line;
			while ((line = br.readLine()) != null) {
				municipios.add(new Municipio(line));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Municipio municipio : municipios) {
			try {
				Document paginaMunicipio = Jsoup.connect(municipio.getLink()).timeout(6000 * 1000).get();

				for (Element link : paginaMunicipio.getElementsByTag("a")) {
					if ("619 × 480 pixels".equals(link.html())) {
						System.out.println(link.attr("href"));
						
						URL url = new URL(link.attr("href"));
						InputStream in = new BufferedInputStream(url.openStream());
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						byte[] buf = new byte[1024];
						int n = 0;
						while (-1!=(n=in.read(buf)))
						{
						   out.write(buf, 0, n);
						}
						out.close();
						in.close();
						byte[] response = out.toByteArray();
						
						FileOutputStream fos = new FileOutputStream("img/" + municipio.getId() + ".png");
						fos.write(response);
						fos.close();
					}
				}
				
//				Response response = ClientBuilder.newClient().target(municipio.getLink()).request().get();
//				if (200 != response.getStatus()) {
//					System.out.println(String.valueOf(response.getStatus()) + " - " + municipio.getId() + " - "
//							+ municipio.getNome() + " - " + municipio.getLink());
//				}
			} catch (IOException e) {
				System.out.println("ERRO REQUEST JSOUP " + municipio.getLink());
				e.printStackTrace();
			}
			;
		}
	}

}
