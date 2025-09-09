public abstract class Medium {
    private String title;
    private int copies;

    protected Medium(String title, int copies) {
        this.title = title;
        this.copies = copies;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public boolean inStock() {
        return copies > 0;
    }

    public void takeOne() {
        if (copies <= 0) throw new IllegalStateException("No copies left!");
        copies--;
    }

    public void returnOne() {
        copies++;
    }
}
