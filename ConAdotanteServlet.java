package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/conAdotante")
public class ConAdotanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConAdotanteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Conexão com o web service
		URL url = new URL("http://localhost/consultaProduto.php");
		HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept","application/json");
		// Lendo a resposta JSON
		InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
		BufferedReader in = new BufferedReader(isr);
		StringBuilder jsonBuilder = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
		jsonBuilder.append(line);
		}
		in.close();
		conn.disconnect();
		// Convertendo o JSON recebido para JSONArray
		JSONArray array = new JSONArray(jsonBuilder.toString());
		//Setando o response para apresentar acentuação
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//Criando objeto para envio do html ao cliente
		PrintWriter pwHTML = response.getWriter();
		//Abrindo as TAGs do arquivo HTML
		pwHTML.println("<!DOCTYPE html>");
		pwHTML.println("<html lang=\"pt-br\">");
		pwHTML.println("<head>");
		pwHTML.println("<meta charset=\"UTF-8\">");
		pwHTML.println("<title>Lista de Usuários</title>");
		//CSS do HTML
		pwHTML.println("<style>");
		pwHTML.println("table { border-collapse: collapse; width: 100%; }");
		pwHTML.println("th, td { border: 1px solid #ccc;padding:8 px;");
		pwHTML.println(" text-align: left; }");
		pwHTML.println("th { background-color: #f2f2f2; }");
		pwHTML.println("</style></head>");
	// Corpo do HTML
	pwHTML.println("<body><h2>Lista de Produto</h2>");
	// Abrindo a TAG da Tabela de resultados
	pwHTML.println("<table>");
	// Definindo os titulo das colunas da tabela
	pwHTML.println("<tr>");
	pwHTML.println("<th>IDProduto</th>");
	pwHTML.println("<th>Nome</th>");
	pwHTML.println("<th>Quantidade</th>");
	pwHTML.println("<th>Identificacao</th>");
	pwHTML.println("<th>Valor</th>");
	pwHTML.println("<th>Disponivel</th>");
	pwHTML.println("<th>Marca</th>");
	pwHTML.println("</tr>");
	// Gerando as linhas de dados da tabela de resultados
	for(

	int i = 0;i<array.length();i++)
	{
		JSONObject user = array.getJSONObject(i);
		pwHTML.println("<tr>");
		pwHTML.println("<td>" + user.getInt("idProduto") + "</td>");
		pwHTML.println("<td>" + user.getString("nmProduto") + "</td>");
		pwHTML.println("<td>" + user.getString("nrQuantidade") + "</td>");
		pwHTML.println("<td>" + user.getString("nrIdentificacao") + "</td>");
		pwHTML.println("<td>" + user.getString("Valor") + "</td>");
		pwHTML.println("<td>" + user.getString("Disponivel") + "</td>");
		pwHTML.println("<td>" + user.getInt("idMarca") + "</td>");
		pwHTML.println("</tr>");
		}
	// Fechando a TAG da tabela de resultados
	pwHTML.println("</table>");
	// Fechando as TAGs do arquivo HTML
	pwHTML.println("</body></html>");
}}
