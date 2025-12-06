# 🖥️ PC Order System — Advanced Java Coursework (CSC8404)

**Author:** Aaditya Kute  
**Module:** Advanced Programming in Java – CSC8404  
**University:** Newcastle University  

This project implements a complete object-oriented **PC Ordering System** designed to demonstrate advanced Java programming principles such as interfaces, inheritance, immutability, Javadoc documentation, exception handling, and comprehensive JUnit testing.

The coursework was completed following the official specification provided as part of CSC8404.  
It includes implementations of **preset PC models**, **custom models**, **customer management**, **credit card validation**, **order processing**, and a **fulfillment reporting system**.

---

## 📝 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [System Design](#system-design)
- [Folder Structure](#folder-structure)
- [How to Run](#how-to-run)
- [How to Test](#how-to-test)
- [Documentation](#documentation)
- [What I Learned](#what-i-learned)
- [Technologies Used](#technologies-used)

---

## 📌 Overview

The **PC Order System** allows customers to create orders consisting of:

- **Preset PC models** (pre-configured by the store)
- **Custom PC models** (user-configured hardware components)
- **Payment using a valid credit card**
- **Order tracking** (cancel, place, fulfill)
- **Aggregation of all fulfilled orders** into a **report**

The design emphasizes:

✔ Clean object-oriented architecture  
✔ Use of interfaces  
✔ Defensive programming  
✔ Proper use of Java Collections  
✔ Correct handling of equals(), hashCode(), and toString()  
✔ Unit testing of every class  

---

## ⭐ Features

### 🖥️ **PC Models**
- **PresetModel** — prebuilt configuration with fixed components.
- **CustomModel** — user-defined model, supports adding/removing parts.
- **ModelGenerator** — factory class to create valid PC models.
- **PresetModelImpl** — canonical implementation of preset models.

---

### 👤 **Customer Management**
- Customer has first name + last name.
- Equality is based on both names.
- Fully tested with normal and boundary cases.

---

### 💳 **Credit Card Validation**
- 8-digit card number validation.
- Expiry date validation using java.util.Date.
- No deprecated date functions used.
- Fully validated through unit tests.

---

### 📦 **Order System**
Each order contains:
- Customer
- One or more PC models
- Valid credit card
- Timestamps
- Order state: **PLACED → FULFILLED / CANCELLED**

Rules implemented:
- A fulfilled order **cannot** be cancelled.
- A cancelled order **cannot** be fulfilled.
- State transitions strictly validated.

---

### 📊 **Report System**
Reports on all fulfilled orders:
- Most frequently purchased **preset model**
- Most frequently selected **component** across custom models
- Customer with the **most fulfilled orders**

Matching the coursework specification.

---

## 🏗️ System Design

Major design principles:

- **Interfaces for extensibility**  
  Preset and custom models share common `ComputerModel` interface.

- **Immutable data structures**  
  Defensive copying prevents external mutation.

- **Factory pattern**  
  `ModelGenerator` manages object creation.

- **Encapsulation and validation**  
  All fields are private; mutators validate inputs.

- **Unit testing first**  
  Every class has a corresponding JUnit test.



