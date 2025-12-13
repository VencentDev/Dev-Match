package com.vinci.devmatch.modules.user.entity;


import com.vinci.devmatch.modules.user.validation.education.ValidEducationList;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "freelancer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreelancerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship to User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String position;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_skills", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_links", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "link")
    private List<String> links = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_languages", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "language")
    private Set<String> languages = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_programming_languages", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "programming_language")
    private Set<String> programmingLanguages = new HashSet<>();

    @ValidEducationList
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_education", joinColumns = @JoinColumn(name = "profile_id"))
    private Set<Education> education = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_certifications", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "certification")
    private Set<String> certifications = new HashSet<>();
}
