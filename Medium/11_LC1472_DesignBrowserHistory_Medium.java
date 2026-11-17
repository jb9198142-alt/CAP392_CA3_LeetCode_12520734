import java.util.*;

class BrowserHistory {
    private List<String> pages = new ArrayList<>();
    private int currentPage;

    public BrowserHistory(String homepage) {
        pages.add(homepage);
        currentPage = 0;
    }

    public void visit(String url) {
        while (pages.size() > currentPage + 1) {
            pages.remove(pages.size() - 1);
        }

        pages.add(url);
        currentPage++;
    }

    public String back(int steps) {
        currentPage = currentPage - steps;

        if (currentPage < 0) {
            currentPage = 0;
        }

        return pages.get(currentPage);
    }

    public String forward(int steps) {
        currentPage = currentPage + steps;

        if (currentPage >= pages.size()) {
            currentPage = pages.size() - 1;
        }

        return pages.get(currentPage);
    }
}
