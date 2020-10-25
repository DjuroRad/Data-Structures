package LibraryAutomationSystem.gtu;

/**
 * public enumeration type that shows the status of the book's location
 */
public enum Status {
    available{
        @Override
        public String toString() {
            return "available";
        }
    }, not_available{
        @Override
        public String toString() {
            return "not available";
        }
    }, reserved{
        @Override
        public String toString() {
            return "reserved";
        }
    }
}

