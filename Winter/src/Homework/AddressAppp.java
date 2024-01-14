package Homework;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 연락처를 나타내는 인터페이스
interface Contact {
    String getName();
    void printInfo();
}

// 주소록에 저장될 개인 연락처 클래스
class PersonalContact implements Contact {
    private String name;
    private String phoneNumber;
    private String email;

    // 생성자
    public PersonalContact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // 인터페이스 메서드 구현
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printInfo() {
        System.out.println("이름: " + name);
        System.out.println("전화번호: " + phoneNumber);
        System.out.println("이메일: " + email);
    }
}

// 주소록 관리 클래스
class AddressBook {
    private List<Contact> contacts; // 연락처 리스트
    private int contactCount; // 현재 연락처 개수
    private static final int MAX_CONTACTS = 10; // 최대 연락처 개수

    // 생성자
    public AddressBook() {
        contacts = new ArrayList<>(); // 연락처를 저장할 ArrayList 초기화
        contactCount = 0;

        // 초기 데이터 값 추가
        addContact(new PersonalContact("이씨", "010-1234-1234", "lee@naver.com"));
        addContact(new PersonalContact("김씨", "010-5678-5678", "kim@naver.com"));
        addContact(new PersonalContact("박씨", "010-0000-0000", "park@naver.com"));
    }

    // 연락처 추가 메서드
    public void addContact(Contact contact) {
        if (contactCount < MAX_CONTACTS) {
            contacts.add(contact);
            contactCount++;
            System.out.println("주소록에 추가되었습니다.");
        } else {
            System.out.println("주소록이 가득 찼습니다. 더 이상 추가할 수 없습니다.");
        }
    }

    // 특정 연락처 검색 메서드
    public void searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact != null && contact.getName().equals(name)) {
                contact.printInfo();
                return;
            }
        }
        System.out.println("주소록에 해당 이름의 연락처가 없습니다.");
    }

    // 주소록 삭제 메서드
    public void deleteContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact != null && contact.getName().equals(name)) {
                contacts.remove(i);
                contactCount--;
                System.out.println("주소록에서 삭제되었습니다.");
                return;
            }
        }
        System.out.println("주소록에 해당 이름의 연락처가 없습니다.");
    }

}

// 프로그램을 실행하는 메인 클래스
public class AddressAppp {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        do {
            printMenu();
            System.out.print("원하는 기능의 번호를 입력하세요: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        System.out.print("검색할 연락처의 이름을 입력하세요: ");
                        String searchName = scanner.nextLine();
                        addressBook.searchContact(searchName);
                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.print("추가할 연락처의 이름을 입력하세요: ");
                        String addName = scanner.nextLine();
                        System.out.print("전화번호를 입력하세요: ");
                        String addPhone = scanner.nextLine();
                        System.out.print("이메일을 입력하세요: ");
                        String addEmail = scanner.nextLine();
                        addressBook.addContact(new PersonalContact(addName, addPhone, addEmail));
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.print("삭제할 연락처의 이름을 입력하세요: ");
                        String deleteName = scanner.nextLine();
                        addressBook.deleteContact(deleteName);
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    default:
                        System.out.println("올바른 번호를 입력하세요.");
                }
            } catch (Exception e) {
                System.err.println("오류가 발생했습니다: " + e.getMessage());
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. 검색");
        System.out.println("2. 주소록 추가");
        System.out.println("3. 주소록 삭제");
        System.out.println("0. 프로그램 종료");
    }
}
