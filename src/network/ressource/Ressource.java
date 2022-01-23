package network.ressource;


    public class Ressource {

        private String name;
        private int count;

        public Ressource(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }
    }


