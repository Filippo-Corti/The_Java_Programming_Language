import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        if (args.length < 1)
            System.exit(1);
        CompanyAds company = new CompanyAds(args[0]);

        Scanner s = new Scanner(System.in);

        System.out.println("Inserisci o aggiorna le campagne:");

        while (s.hasNextLine()) {
            String[] in = s.nextLine().split(" ");

            try {
                switch (in[2]) {
                    case "open":
                        AdCampaign ac = null;
                        switch (in[1]) {
                            case "Social":
                                ac = new AdCampaignSocial(in[0]);
                                break;
                            case "WebRestyle":
                                ac = new AdCampaignWebRestyling(in[0],
                                        (in[2].equals("open")) ? Double.parseDouble(in[3]) : 0);
                                break;
                            default:
                                System.out.println("Tipo di Campagna non riconosciuto");
                                continue; // Non bellissimo ma meglio di un System.exit presumo
                        }
                        company.add(ac);
                        break;
                    case "close":
                        company.get(in[0]).close();
                        break;
                    case "update":
                        switch (in[1]) {
                            case "Social":
                                ((AdCampaignSocial) company.get(in[0])).updatenVis(Integer.parseInt(in[3]));
                                ((AdCampaignSocial) company.get(in[0])).updatenLike(Integer.parseInt(in[4]));
                                break;
                            case "WebRestyle":
                                ((AdCampaignWebRestyling) company.get(in[0])).updatenVis(Integer.parseInt(in[3]),
                                        Double.parseDouble(in[4]));
                                break;
                            default:
                                System.out.println("Tipo di Campagna non riconosciuto");
                                continue; // Non bellissimo ma meglio di un System.exit presumo
                        }
                        break;
                    default:
                        System.out.println("Operazione non riconosciuta");
                        break;
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Input non valido");
            } catch (NullPointerException | IllegalArgumentException | CampaignClosedException | CampaignExistsException
                    | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println();
        System.out.println(company);
    }

}
