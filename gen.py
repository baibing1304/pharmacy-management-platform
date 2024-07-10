import json
import os

# JSON数据
data = {
	"工程结构": "pharmacy-management-platform/\n├── src/\n│   ├── main/\n│   │   ├── java/\n│   │   │   └── com/\n│   │   │       └── example/\n│   │   │           └── pharmacymanagement/\n│   │   │               ├── controller/\n│   │   │               │   └── UserController.java\n│   │   │               │   └── MedicineController.java\n│   │   │               │   └── SalesController.java\n│   │   │               │   └── InventoryController.java\n│   │   │               │   └── SupplierController.java\n│   │   │               │   └── ReportController.java\n│   │   │               ├── service/\n│   │   │               │   └── UserService.java\n│   │   │               │   └── MedicineService.java\n│   │   │               │   └── SalesService.java\n│   │   │               │   └── InventoryService.java\n│   │   │               │   └── SupplierService.java\n│   │   │               │   └── ReportService.java\n│   │   │               ├── repository/\n│   │   │               │   └── UserRepository.java\n│   │   │               │   └── MedicineRepository.java\n│   │   │               │   └── SalesRepository.java\n│   │   │               │   └── InventoryRepository.java\n│   │   │               │   └── SupplierRepository.java\n│   │   │               ├── model/\n│   │   │               │   └── User.java\n│   │   │               │   └── Medicine.java\n│   │   │               │   └── Sales.java\n│   │   │               │   └── Inventory.java\n│   │   │               │   └── Supplier.java\n│   │   │               ├── config/\n│   │   │               │   └── SecurityConfig.java\n│   │   └── resources/\n│   │       ├── application.properties\n│   │       └── data.sql\n│   └── test/\n├── pom.xml\n└── README.md",
	"工程代码": [
		{
			"代码路径": "pharmacy-management-platform/pom.xml",
			"代码内容": "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n    <modelVersion>4.0.0</modelVersion>\n    <groupId>com.example</groupId>\n    <artifactId>pharmacy-management-platform</artifactId>\n    <version>1.0.0</version>\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.7.3</version>\n        <relativePath /> <!-- lookup parent from repository -->\n    </parent>\n    <dependencies>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-data-jpa</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>mysql</groupId>\n            <artifactId>mysql-connector-java</artifactId>\n            <scope>runtime</scope>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-security</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>io.jsonwebtoken</groupId>\n            <artifactId>jjwt</artifactId>\n            <version>0.9.1</version>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-test</artifactId>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>\n    <build>\n        <plugins>\n            <plugin>\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-maven-plugin</artifactId>\n            </plugin>\n        </plugins>\n    </build>\n</project>"
		},
		{
			"代码路径": "pharmacy-management-platform/src/main/resources/application.properties",
			"代码内容": "spring.datasource.url=jdbc:mysql://localhost:3306/pharmacy\nspring.datasource.username=root\nspring.datasource.password=rootpassword\nspring.jpa.hibernate.ddl-auto=update\nspring.jpa.show-sql=true\nspring.jpa.properties.hibernate.format_sql=true\njwt.secret=mySecretKey\njwt.expiration=3600000"
		},
		{
			"代码路径": "pharmacy-management-platform/src/main/java/com/example/pharmacymanagement/model/User.java",
			"代码内容": "package com.example.pharmacymanagement.model;\n\nimport javax.persistence.*;\nimport java.time.LocalDateTime;\n\n@Entity\n@Table(name = \"users\")\npublic class User {\n    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n    private Long id;\n\n    @Column(nullable = false, unique = true, length = 50)\n    private String username;\n\n    @Column(nullable = false, length = 100)\n    private String password;\n\n    @Column(nullable = false, length = 10)\n    private String role;\n\n    @Column(name = \"created_at\")\n    private LocalDateTime createdAt;\n\n    @Column(name = \"updated_at\")\n    private LocalDateTime updatedAt;\n\n    // Getters and setters\n}"
		},
		{
			"代码路径": "pharmacy-management-platform/src/main/java/com/example/pharmacymanagement/repository/UserRepository.java",
			"代码内容": "package com.example.pharmacymanagement.repository;\n\nimport com.example.pharmacymanagement.model.User;\nimport org.springframework.data.jpa.repository.JpaRepository;\nimport java.util.Optional;\n\npublic interface UserRepository extends JpaRepository<User, Long> {\n    Optional<User> findByUsername(String username);\n}"
		},
		{
			"代码路径": "pharmacy-management-platform/src/main/java/com/example/pharmacymanagement/service/UserService.java",
			"代码内容": "package com.example.pharmacymanagement.service;\n\nimport com.example.pharmacymanagement.model.User;\nimport com.example.pharmacymanagement.repository.UserRepository;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;\nimport org.springframework.stereotype.Service;\n\n@Service\npublic class UserService {\n    @Autowired\n    private UserRepository userRepository;\n\n    @Autowired\n    private BCryptPasswordEncoder bCryptPasswordEncoder;\n\n    public User registerUser(User user) {\n        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));\n        return userRepository.save(user);\n    }\n\n    public User findByUsername(String username) {\n        return userRepository.findByUsername(username).orElse(null);\n    }\n}"
		},
		{
			"代码路径": "pharmacy-management-platform/src/main/java/com/example/pharmacymanagement/controller/UserController.java",
			"代码内容": "package com.example.pharmacymanagement.controller;\n\nimport com.example.pharmacymanagement.model.User;\nimport com.example.pharmacymanagement.service.UserService;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.http.ResponseEntity;\nimport org.springframework.security.authentication.AuthenticationManager;\nimport org.springframework.security.authentication.UsernamePasswordAuthenticationToken;\nimport org.springframework.security.core.Authentication;\nimport org.springframework.security.core.context.SecurityContextHolder;\nimport org.springframework.web.bind.annotation.*;\n\n@RestController\n@RequestMapping(\"/api/users\")\npublic class UserController {\n    @Autowired\n    private UserService userService;\n\n    @Autowired\n    private AuthenticationManager authenticationManager;\n\n    @PostMapping(\"/register\")\n    public ResponseEntity<User> registerUser(@RequestBody User user) {\n        return ResponseEntity.ok(userService.registerUser(user));\n    }\n\n    @PostMapping(\"/login\")\n    public ResponseEntity<String> loginUser(@RequestBody User user) {\n        Authentication authentication = authenticationManager.authenticate(\n                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));\n        SecurityContextHolder.getContext().setAuthentication(authentication);\n        return ResponseEntity.ok(\"User logged in successfully\");\n    }\n}"
		},
		{
			"代码路径": "pharmacy-management-platform/src/main/java/com/example/pharmacymanagement/config/SecurityConfig.java",
			"代码内容": "package com.example.pharmacymanagement.config;\n\nimport org.springframework.context.annotation.Bean;\nimport org.springframework.context.annotation.Configuration;\nimport org.springframework.security.config.annotation.web.builders.HttpSecurity;\nimport org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;\nimport org.springframework.security.config.http.SessionCreationPolicy;\nimport org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;\nimport org.springframework.security.crypto.password.PasswordEncoder;\n\n@Configuration\npublic class SecurityConfig extends WebSecurityConfigurerAdapter {\n    @Override\n    protected void configure(HttpSecurity http) throws Exception {\n        http.csrf().disable()\n            .authorizeRequests()\n            .antMatchers(\"/api/users/register\", \"/api/users/login\").permitAll()\n            .anyRequest().authenticated()\n            .and()\n            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);\n    }\n\n    @Bean\n    public PasswordEncoder passwordEncoder() {\n        return new BCryptPasswordEncoder();\n    }\n}"
		}
	]
}







# 根据JSON数据生成源码文件和目录结构
def create_file(path, content):
    # 如果path中包含斜杆
    if '/' in path:
        os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)

for file in data["工程代码"]:
    print(file["代码路径"])
    print(file["代码内容"])
    create_file(file["代码路径"], file["代码内容"])
