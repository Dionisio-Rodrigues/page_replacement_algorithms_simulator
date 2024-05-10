public class Main {

    public static class Page {
        public int reference;
        public int r;
        public int counter;

        public Page() {
        }

        public Page(int reference) {
            this.reference = reference;
            this.counter = 1;
            this.r = 1;
        }

        public Page(int reference, int r) {
            this.reference = reference;
            this.counter = 1;
            this.r = r;
        }
    }

    public static class MainMemoryQueue {

        static class Node {
            public Page page;
            public Node next;

            public Node(Page page, Node next) {
                this.page = page;
                this.next = next;
            }

            public Node(Page page) {
                this.page = page;
                this.next = null;
            }

            public Node() {
            }
        }

        Node head;
        Node tail;
        int size;
        int maxLength;

        public MainMemoryQueue(int maxLength) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            this.maxLength = maxLength;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == maxLength;
        }

        public Page add(Page page) {
            if (isFull())
                return null;

            Node newNode = new Node(page);
            if (isEmpty()) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                this.tail = newNode;
            }
            this.size++;
            return page;
        }

        public void remove() {
            if (isEmpty())
                return;

            this.head = this.head.next;
            this.size--;

            if (isEmpty())
                this.tail = null;
        }

        public boolean contains(Page page) {
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == page.reference)
                    return true;
                node = node.next;
            }
            return false;
        }

        public Page get(int reference) {
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == reference)
                    return node.page;
                node = node.next;
            }
            return null;
        }
    }

    public static class MainMemoryCircularQueue {

        static class Node {
            public Page page;
            public Node next;

            public Node(Page page, Node next) {
                this.page = page;
                this.next = next;
            }

            public Node(Page page) {
                this.page = page;
            }

            public Node() {
            }
        }

        Node head;
        Node tail;
        int size;
        int maxLength;

        public MainMemoryCircularQueue(int maxLength) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            this.maxLength = maxLength;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == maxLength;
        }

        public Page add(Page page) {
            if (isFull())
                return null;

            Node newNode = new Node(page);
            if (isEmpty()) {
                this.head = newNode;
                this.tail = newNode;
                tail.next = head;
            } else {
                this.tail.next = newNode;
                this.tail = newNode;
                this.tail.next = this.head;
            }
            this.size++;
            return page;
        }

        public Page swap(Page page, int index) {
            if (isEmpty())
                return null;
            if (index > size - 1 || index < 0)
                return null;

            Node node = this.head;
            for (int i = 0; i < index; i++)
                node = node.next;
            node.page = page;
            return page;
        }

        public boolean contains(int reference) {
            if (isEmpty())
                return false;

            Node node = this.head;
            do {
                if (node.page.reference == reference)
                    return true;
                node = node.next;
            } while (!node.equals(this.head));
            return false;
        }

        public Page get(int reference) {
            if (isEmpty())
                return null;

            Node node = this.head;
            do {
                if (node.page.reference == reference)
                    return node.page;
                node = node.next;
            } while (!node.equals(this.head));
            return null;
        }

        public Page getByIndex(int index) {
            if (isEmpty())
                return null;
            if (index > size - 1 || index < 0)
                return null;

            Node node = this.head;
            for (int i = 0; i < index; i++)
                node = node.next;
            return node.page;
        }
    }

    public static class MainMemoryOrderedList {

        static class Node {
            public Page page;
            public Node next;
            public Node previous;

            public Node(Page page, Node next, Node previous) {
                this.page = page;
                this.next = next;
                this.previous = previous;
            }

            public Node(Page page) {
                this.page = page;
                this.next = null;
                this.previous = null;
            }

            public Node() {
            }
        }

        Node head;
        Node tail;
        int size;
        int maxLength;

        public MainMemoryOrderedList(int maxLength) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            this.maxLength = maxLength;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == maxLength;
        }

        public Page add(Page page) {
            if (isFull())
                return null;

            Node newNode = new Node(page);
            if (isEmpty()) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                newNode.next = this.head;
                this.head.previous = newNode;
                this.head = newNode;
            }
            this.size++;
            return page;
        }

        public void removeLastNode() {
            if (isEmpty())
                return;
        
            if (this.tail.equals(head)) {
                this.tail = null;
                this.head = null;
            } else {
                this.tail.previous.next = null;
                this.tail = this.tail.previous;
            }
        
            this.size--;
        
            if (isEmpty())
                this.tail = null;
        }

        public boolean contains(int reference) {
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == reference)
                    return true;
                node = node.next;
            }
            return false;
        }

        public Page get(int reference) {
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == reference)
                    return node.page;
                node = node.next;
            }
            return null;
        }

        public Page movToStart(int reference) {
            if (this.head == null)
                return null;
        
            if (this.head.page.reference == reference)
                return this.head.page;
        
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == reference) {
                    if (node == this.tail) {
                        // Se o nó com a referência desejada for a cauda da lista
                        this.tail = node.previous;
                        node.previous.next = null;
                    } else {
                        // Se o nó com a referência desejada não for a cauda da lista
                        node.previous.next = node.next;
                        node.next.previous = node.previous;
                    }
        
                    node.next = this.head;
                    this.head.previous = node;
                    node.previous = null;
                    this.head = node;
        
                    return node.page;
                }
                node = node.next;
            }
        
            return null;
        }
        
    }

    public static class MainMemoryList {

        static class Node {
            public Page page;
            public Node next;
            public Node previous;

            public Node(Page page, Node next, Node previous) {
                this.page = page;
                this.next = next;
                this.previous = previous;
            }

            public Node(Page page) {
                this.page = page;
                this.next = null;
                this.previous = null;
            }

            public Node() {
            }
        }

        Node head;
        Node tail;
        int size;
        int maxLength;

        public MainMemoryList(int maxLength) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            this.maxLength = maxLength;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == maxLength;
        }

        public Page add(Page page) {
            if (isFull())
                return null;

            Node newNode = new Node(page);
            if (isEmpty()) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                this.tail = newNode;
            }
            this.size++;
            return page;
        }

        public boolean contains(int reference) {
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == reference)
                    return true;
                node = node.next;
            }
            return false;
        }

        public Page get(int reference) {
            Node node = this.head;
            while (node != null) {
                if (node.page.reference == reference)
                    return node.page;
                node = node.next;
            }
            return null;
        }

        public Page swap(Page page, int index) {
            if (isEmpty())
                return null;
            if (index > size - 1 || index < 0)
                return null;

            Node node = this.head;
            for (int i = 0; i < index; i++)
                node = node.next;
            node.page = page;
            return page;
        }

        public Page getByIndex(int index) {
            if (isEmpty())
                return null;
            if (index > size - 1 || index < 0)
                return null;

            Node node = this.head;
            for (int i = 0; i < index; i++)
                node = node.next;
            return node.page;
        }
    }

    public static class AlgorithmFIFO {
        MainMemoryQueue mainMemoryQueue;

        public AlgorithmFIFO(int framesAmount) {
            this.mainMemoryQueue = new MainMemoryQueue(framesAmount);
        }

        public int toExecute(int[] pageReferences) {
            int pageFault = 0;

            for (int reference : pageReferences) {
                Page page = new Page(reference);

                if (mainMemoryQueue.contains(page))
                    continue;

                pageFault++;

                if (mainMemoryQueue.size < mainMemoryQueue.maxLength) {
                    mainMemoryQueue.add(page);
                    continue;
                }

                mainMemoryQueue.remove();
                mainMemoryQueue.add(page);
            }

            return pageFault;
        }
    }

    public static class AlgorithmClock {
        MainMemoryCircularQueue mainMemoryCircularQueue;

        public AlgorithmClock(int framesAmount) {
            this.mainMemoryCircularQueue = new MainMemoryCircularQueue(framesAmount);
        }

        public int toExecute(int[] pageReferences) {
            int pageFault = 0;
            int pointer = -1;

            for (int reference : pageReferences) {
                if (mainMemoryCircularQueue.contains(reference)) {
                    mainMemoryCircularQueue.get(reference).r = 1;
                    continue;
                }

                Page page = new Page(reference);
                pageFault++;

                if (mainMemoryCircularQueue.size < mainMemoryCircularQueue.maxLength) {
                    mainMemoryCircularQueue.add(page);
                    continue;
                }

                while (true) {
                    pointer++;
                    if (pointer >= mainMemoryCircularQueue.maxLength)
                        pointer = 0;

                    page = mainMemoryCircularQueue.getByIndex(pointer);
                    if (page.r == 0) {
                        mainMemoryCircularQueue.swap(page, pointer);
                        break;
                    }

                    page.r = 0;
                }
            }

            return pageFault;
        }
    }

    public static class AlgorithmLRU {
        MainMemoryOrderedList mainMemoryOrderedList;

        public AlgorithmLRU(int framesAmount) {
            this.mainMemoryOrderedList = new MainMemoryOrderedList(framesAmount);
        }

        public int toExecute(int[] pageReferences) {
            int pageFault = 0;

            for (int reference : pageReferences) {

                if (mainMemoryOrderedList.contains(reference)) {
                    this.mainMemoryOrderedList.movToStart(reference);
                    continue;
                }

                Page page = new Page(reference);
                pageFault++;

                if (mainMemoryOrderedList.size < mainMemoryOrderedList.maxLength) {
                    mainMemoryOrderedList.add(page);
                    continue;
                }

                mainMemoryOrderedList.removeLastNode();
                mainMemoryOrderedList.add(page);
            }

            return pageFault;
        }
    }

    public static class AlgorithmNFU {
        MainMemoryList mainMemoryList;

        public AlgorithmNFU(int framesAmount) {
            this.mainMemoryList = new MainMemoryList(framesAmount);
        }

        public int toExecute(int[] pageReferences) {
            int pageFault = 0;

            for (int reference : pageReferences) {
                Page page;

                if (mainMemoryList.contains(reference)) {
                    page = this.mainMemoryList.get(reference);
                    page.r++;
                } else {
                    page = new Page(reference);
                    pageFault++;

                    if (mainMemoryList.size < mainMemoryList.maxLength)
                        mainMemoryList.add(page);
                    else {
                        Page smaller = mainMemoryList.getByIndex(0);
                        int indexForSwap = 0;
                        for (int i = 1; i < mainMemoryList.maxLength; i++) {
                            Page comparing = mainMemoryList.getByIndex(i);
                            if (smaller.counter > comparing.counter) {
                                smaller = comparing;
                                indexForSwap = i;
                            }
                        }
                        mainMemoryList.swap(page, indexForSwap);
                    }

                }

                if (page.r == 1) {
                    page.counter++;
                    page.r = 0;
                }

            }

            return pageFault;
        }
    }

    public static void main(String[] args) {
        int[] references = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1,  1, 2, 0, 1, 7, 0, 1, 4, 2, 3, 0, 3, 2, 1, 2, 0, 3, 0,1, 2, 0, 1, 7, 0, 1, 4, 2};
        int framesAmount = 3;

        AlgorithmFIFO algorithmFIFO = new AlgorithmFIFO(framesAmount);
        AlgorithmClock algorithmClock = new AlgorithmClock(framesAmount);
        AlgorithmLRU algorithmLRU = new AlgorithmLRU(framesAmount);
        AlgorithmNFU algorithmNFU = new AlgorithmNFU(framesAmount);

        System.out.println("Page faults (FIFO): " + algorithmFIFO.toExecute(references));
        System.out.println("Page faults (Clock): " + algorithmClock.toExecute(references));
        System.out.println("Page faults (LRU): " + algorithmLRU.toExecute(references));
        System.out.println("Page faults (NFU): " + algorithmNFU.toExecute(references));
    }
}