package br.vp.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Classe que atua como Filter e que é utilizada para verificar se a sessão HttpSession
 * está válida ou não para uma determinada requisição ao servidor.<br/>
 * <br/>
 * O código verifica a validade de uma requisição baseado na URL enviada para o servidor.
 * Se a URL não estiver em uma lista de URL's liberadas, será efetuado a verificação se
 * a sessão está iniciada, se o atributo indicativo de Login efetuado com sucesso existe
 * e se é da classe correta.<br/>
 * <br/>
 *
 * @author 15/03/2014 - &copy; José Augusto Martins Nieviadonski
 *
 */
public class FiltroLogin implements Filter
{
    // Nomes dos parâmetros de inicialização
    private static final String ENDERECO_LOGOUT = "ENDERECO_LOGOUT";
    private static final String CLASSE_ATRIBUTO = "CLASSE_ATRIBUTO";
    private static final String NOME_ATRIBUTO   = "NOME_ATRIBUTO";
    private static final String URL_LIBERADAS   = "URL_LIBERADAS";

    // Lista de erros do filtro
    private static final String ERRO_DEBUG           = "FiltroLogin - Opção de depuração não configurada";
    private static final String ERRO_ENDERECO_LOGOUT = "FiltroLogin - Endereço de logout não configurado";
    private static final String ERRO_NOME_ATRIBUTO   = "FiltroLogin - Nome do atributo não configurado";
    private static final String ERRO_CLASSE_ATRIBUTO = "FiltroLogin - Classe do atributo não configurado";
    private static final String ERRO_LISTA_URLS      = "FiltroLogin - Lista de URL's liberadas não configurada";
    private static final String ERRO_LEITURA_URLS    = "FiltroLogin - Problemas na leitura das URL's liberadas";

    // Atributos
    private FilterConfig        mConfig         = null;                   // Configuração do filtro
    private String              mEnderecoLogout = "";                     // Endere�o a ser usado para o logout
    private String              mNomeAtrib      = "";                     // Nome do atributo salvo no HttpSession
    private String              mClasseAtrib    = "";                     // Nome completo da classe do atributo de sessão
    private Set<String>         mUrlLiberadas   = new HashSet<String>();  // Lista de URL's que não precisam de login

    /**
     * Esse método á executado quando a instância do filtro é criada pelo container do
     * servidor.<br/>
     * <br>
     * A classe ao ser instanciada pelo container do servidor, carrega os parâmetros de
     * inicialização abaixo, configurados no arquivo <code>web.xml</code>, na definição do filtro:<br/>
     * <br/>
     *
     * <b>DEBUG</b>           - Flag indicativo de depuração ou não na console. o valor <code>true</code>
     *                          irá ativar a mostra das mensagens da console<br/>
     * <b>ENDERECO_LOGOUT</b> - Endereço do servlet de logout do sistema.<br/>
     * <b>NOME_ATRIBUTO</b>   - Nome do atributo que indicará que a sessão está iniciada e ok.<br/>
     * <b>CLASSE_ATRIBUTO</b> - Nome da classe, com o pacote, que representa o objeto
     *                          do atributo salvo indicando que a sessão está iniciada.<br/>
     * <b>URL_LIBERADAS</b>   - Lista de URL's que não precisam validar a sessão, sendo chamadas
     *                          diretamente pelo filtro.<br/>
     * <br/>
     * Caso algum parâetro de configuração não exista, esteja vazio ou com valor inválido, será lançado uma
     * exceção <code>ServletException</code> pois a falta de algum parâmetro de
     * configuração não permitirá a operação adequada da função do filtro.<br/>
     * <br/>
     */
    @Override
    public void init(FilterConfig pConfig) throws ServletException
    {
        // Salvando o objeto FilterConfig recebido
        mConfig = pConfig;

        // Recuperando o contexto da aplicação
        String tContexto = ((HttpServletRequest) mConfig.getServletContext()).getContextPath();

        // Recuperando o endereço de logout
        mEnderecoLogout = mConfig.getInitParameter(ENDERECO_LOGOUT);
        if (mEnderecoLogout == null || mEnderecoLogout.trim().isEmpty())
            throw new ServletException(ERRO_ENDERECO_LOGOUT);

        // Recuperando o nome do atributo de validação de login
        mNomeAtrib = mConfig.getInitParameter(NOME_ATRIBUTO);
        if (mNomeAtrib == null || mNomeAtrib.trim().isEmpty())
            throw new ServletException(ERRO_NOME_ATRIBUTO);

        // Recuperando o nome da classe do atributo de validação
        mClasseAtrib = mConfig.getInitParameter(CLASSE_ATRIBUTO);
        if (mClasseAtrib == null || mClasseAtrib.trim().isEmpty())
            throw new ServletException(ERRO_CLASSE_ATRIBUTO);

        // Retirando os espaços iniciais e finais, caso haja
        mEnderecoLogout = mEnderecoLogout.trim();
        mNomeAtrib = mNomeAtrib.trim();
        mClasseAtrib = mClasseAtrib.trim();

        // Recuperando a lista de URL liiberadas de login
        String tListaUrl = mConfig.getInitParameter(URL_LIBERADAS);
        if (tListaUrl == null || tListaUrl.trim().isEmpty())
            throw new ServletException(ERRO_LISTA_URLS);

        // Retirando os espaços iniciais, finais e espaços entre as URLs
        tListaUrl = tListaUrl.replaceAll(" +", "");

        try
        {
            // Transformando a lista de url em um stream de leitura
            StringReader tArq1 = new StringReader(tListaUrl);
            BufferedReader tArq2 = new BufferedReader(tArq1);

            // Fica lendo até que não tenha mais URL
            while (true)
            {
                // Lê uma linha do stream, obtendo uma URL
                String tLinha = tArq2.readLine();

                // Caso não existam mais URLs, sai do loop
                if (tLinha == null)
                    break;

                // Coloca a URL lida no conjunto de URls liberadas
                mUrlLiberadas.add(tContexto + tLinha);
            }
        }
        catch (IOException tExcept)
        {
            // Caso ocorra exceção, mostrar a pilha no log do servidor
            tExcept.printStackTrace();

            // Lançar a exceção genérica para tratamento padrão pelo servidor
            throw new ServletException(ERRO_LEITURA_URLS, tExcept);
        }
    }

    /**
     * Método que é chamado a toda e qualquer requisição feita ao servidor, pois a configuração
     * no arquivo <code>web.xml</code> indica isso.<br/>
     * <br/>
     * O método recupera a URL recebida e verifica se a mesma está ou não na lista de URL's
     * liberadas. Caso a URL esteja liberada, o filtro nãoo faz nada e segue o processamento,
     * sem a verificação do login.<br/>
     * <br/>
     * Caso a URL não esteja na lista de URL's liberadas, o método acessa a sessão
     * HttpSession e verifica se existe o atributo indicativo de login efetuado com sucesso
     * e a classe do mesmo. Caso tudo esteja correto, o filtro segue com o processamento
     * da requisição, pois existe a evidência que a requisição faz parte de uma sessão
     * válida de trabalho.<br/>
     * <br/>
     * Caso a verificação da sessão falhe, o método redireciona a requisição para endereço
     * configurado de logout, geralmente um servlet.
     */
    @Override
    public void doFilter(ServletRequest pReq, ServletResponse pResp, FilterChain pChain)
                    throws IOException, ServletException
    {
        // Transformando a requisição geral em uma requisição HTTP
        HttpServletRequest tReq = (HttpServletRequest) pReq;

        // Obtendo a URL chamada e mostrando no log da aplicação
        String tURL = tReq.getRequestURI();

        // Obtendo o contexto da aplicação
        ServletContext tContexto = mConfig.getServletContext();

        // Caso a URL não esteja na lista de URLs liberadas, verifica se está logado no sistema
        if (!mUrlLiberadas.contains(tURL))
        {
            // Verificando se a sessão está iniciada, existe o atributo configurado e ele é da classe indicada
            HttpSession tSessao = tReq.getSession(false);
            if (tSessao == null ||
                tSessao.getAttribute(mNomeAtrib) == null ||
                !tSessao.getAttribute(mNomeAtrib).getClass().getName().equals(mClasseAtrib))
            {
                // Caso não esteja logado, segue para a página de logout configurada
                RequestDispatcher tDisp = tContexto.getRequestDispatcher(mEnderecoLogout);
                tDisp.forward(pReq, pResp);

                // Encerra o método sem seguir adiante no processamento
                return;
            }
        }

        // Seguindo a execução da requisição
        pChain.doFilter(pReq, pResp);
    }

    /**
     * Método obrigatório devido a implementaçãoo da interface <code>Filter</code> mas
     * não é utilizado nesse código.
     */
    @Override
    public void destroy()
    {
        // Nada a declarar
    }
}
