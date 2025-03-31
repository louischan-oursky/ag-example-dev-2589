using Duende.IdentityModel.Client;
using Duende.IdentityModel.OidcClient;

internal class Program
{
    static void Main(string[] args)
    {
        MainAsync().GetAwaiter().GetResult();
    }

    static async Task MainAsync()
    {
        var clientOptions = new OidcClientOptions
        {
            Authority = "https://louischan.authgear.cloud",
            ClientId = "baacd0d565eb7f8a",
            RedirectUri = "https://localhost/",
            Scope = "openid offline_access"
        };
        var client = new OidcClient(clientOptions);
        var preparation = await client.PrepareLoginAsync();
        Console.Error.Write("Copy the following URL to your browser and proceed the login\n\n");
        Console.Error.Write("{0}\n\n", preparation.StartUrl);
        Console.Error.Write("After you have finished, paste the whole redirect URI here\n\n");

        var redirectURI = Console.ReadLine();

        Console.Error.Write("\nYou entered: {0}\n\n", redirectURI);

        var result = await client.ProcessResponseAsync(redirectURI, preparation);
        Console.Write("{0}\n", result.TokenResponse.Raw);
    }
}
