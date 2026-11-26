<?php
class Contact
{
    private string $name;
    private string $lastNames;
    private int $phone;
    private string $mail;

    public function __construct(string $name, string $lastNames, int $phone, string $mail)
    {
        $this->name = $name;
        $this->lastNames = $lastNames;
        $this->phone = $phone;
        $this->mail = $mail;
    }

    public function getName(): string
    {
        return $this->name;
    }
    public function getLastNames(): string
    {
        return $this->lastNames;
    }
    public function getPhone(): int
    {
        return $this->phone;
    }
    public function getMail(): string
    {
        return $this->mail;
    }

    public function setName(string $newValue): void
    {
        $this->name = $newValue;
    }
    public function setLastNames(string $newValue): void
    {
        $this->lastNames = $newValue;
    }
    public function setPhone(int $newValue): void
    {
        $this->phone = $newValue;
    }
    public function setMail(string $newValue): void
    {
        $this->mail = $newValue;
    }

    public static function createContact(): Contact
    {
        echo "Introduce Nombre del contacto: ";
        $name = trim(readline());

        echo "Introduce Apellidos del contacto: ";
        $lastNames = trim(readline());

        echo "Introduce Telefono del contacto: ";
        $phone = (int)trim(readline());

        echo "Introduce Correo del contacto: ";
        $mail = trim(readline());

        return new Contact(
            $name,
            $lastNames,
            $phone,
            $mail
        );
    }

    public function __toString(): string
    {
        return "Contact Info\n" .
            "Nombre Completo: " . $this->getName() . " " . $this->getLastNames() . "\n" .
            "Correo Electronico: " . $this->getMail() . "\n" .
            "Telefono: " . $this->getPhone();
    }
}
