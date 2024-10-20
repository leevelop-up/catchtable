-- Member 테이블 생성
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회원 번호 (고유 식별자)',
                        name VARCHAR(12) NOT NULL COMMENT '회원 이름 (최대 한글 4글자)',
                        phone_number VARCHAR(15) NOT NULL COMMENT '전화번호',
                        gender ENUM('M', 'F') NOT NULL COMMENT '성별 (M: 남성, F: 여성)',
                        birth DATE NOT NULL COMMENT '생년월일',
                        email VARCHAR(100) NOT NULL COMMENT '이메일 주소',
                        password VARCHAR(255) NOT NULL COMMENT '비밀번호',
                        role VARCHAR(20) NOT NULL COMMENT '역할 (예: 사용자, 관리자)',
                        create_date_time DATETIME NOT NULL COMMENT '생성 날짜',
                        update_date_time DATETIME NULL COMMENT '수정 날짜',
                        UNIQUE (email)
);

-- Shop 테이블 생성
CREATE TABLE shop (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '가게 ID (고유 식별자)',
                      name VARCHAR(100) NOT NULL COMMENT '가게 이름',
                      rating DECIMAL(3, 1) NOT NULL COMMENT '가게 평점',
                      category VARCHAR(50) NOT NULL COMMENT '가게 카테고리',
                      city VARCHAR(50) NOT NULL COMMENT '도시',
                      district VARCHAR(50) NOT NULL COMMENT '군/구',
                      capacity INT NOT NULL COMMENT '수용 가능 인원',
                      opening_time TIME NOT NULL COMMENT '영업 시작 시간',
                      closing_time TIME NOT NULL COMMENT '영업 종료 시간',
                      create_date_time DATETIME NOT NULL COMMENT '생성 날짜',
                      update_date_time DATETIME NULL COMMENT '수정 날짜'
);

-- Reservation 테이블 생성
CREATE TABLE reservation (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '예약 ID (고유 식별자)',
                             member_no BIGINT NOT NULL COMMENT '회원 번호 (참조: member.member_no)',
                             shop_id BIGINT NOT NULL COMMENT '가게 ID (참조: shop.shop_id)',
                             date DATETIME NOT NULL COMMENT '예약 날짜',
                             time VARCHAR(10) NOT NULL COMMENT '예약 시간',
                             status TINYINT NOT NULL COMMENT '예약 상태 (0: 예약, 1: 예약 취소)',
                             people_count INT NOT NULL COMMENT '예약 인원 수',
                             create_date_time DATETIME NOT NULL COMMENT '생성 날짜',
                             update_date_time DATETIME NULL COMMENT '수정 날짜',
                             FOREIGN KEY (member_no) REFERENCES member(id),
                             FOREIGN KEY (shop_id) REFERENCES shop(id)
);

-- Waiting 테이블 생성
CREATE TABLE waiting (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '대기 ID (고유 식별자)',
                         member_no BIGINT NOT NULL COMMENT '회원 번호 (참조: member.member.no)',
                         shop_id BIGINT NOT NULL COMMENT '가게 ID (참조: shop.shop_id)',
                         people_count INT NOT NULL COMMENT '대기 인원 수',
                         date DATETIME NOT NULL COMMENT '예약 날짜',
                         time VARCHAR(10) NOT NULL COMMENT '예약 시간',
                         create_date_time DATETIME NOT NULL COMMENT '생성 날짜',
                         update_date_time DATETIME NULL COMMENT '수정 날짜',
                         FOREIGN KEY (member_no) REFERENCES member(id),
                         FOREIGN KEY (shop_id) REFERENCES shop(id)
);

-- Menu 테이블 생성
CREATE TABLE menu (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '메뉴 ID (고유 식별자)',
                      shop_id BIGINT NOT NULL COMMENT '가게 ID (참조: shop.shop_id)',
                      name VARCHAR(100) NOT NULL COMMENT '메뉴 이름',
                      price INT NOT NULL COMMENT '가격',
                      description TEXT COMMENT '메뉴 설명',
                      create_date_time DATETIME NOT NULL COMMENT '생성 날짜',
                      update_date_time DATETIME NULL COMMENT '수정 날짜',
                      FOREIGN KEY (shop_id) REFERENCES shop(id)
);

-- Owner 테이블 생성
CREATE TABLE owner (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '점주 ID (고유 식별자)',
                       shop_id BIGINT NOT NULL COMMENT '가게 ID (참조: shop.shop_id)',
                       name VARCHAR(12) NOT NULL COMMENT '점주 이름 (최대 한글 4글자)',
                       email VARCHAR(100) NOT NULL COMMENT '이메일',
                       password VARCHAR(255) NOT NULL COMMENT '비밀번호',
                       phone_number VARCHAR(15) NOT NULL COMMENT '전화번호',
                       gender ENUM('M', 'F') NOT NULL COMMENT '성별 (M: 남성, F: 여성)',
                       role VARCHAR(20) NOT NULL COMMENT '역할 (예: 관리자)',
                       create_date_time DATETIME NOT NULL COMMENT '생성 날짜',
                       update_date_time DATETIME NULL COMMENT '수정 날짜',
                       FOREIGN KEY (shop_id) REFERENCES shop(id),
                       UNIQUE (email)
);