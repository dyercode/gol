{
  description = "Game of life impl";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    dev.url = "github:dyercode/dev";
  };

  outputs =
    {
      self,
      nixpkgs,
      dev,
    }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        nativeBuildInputs = with pkgs; [
          sbt
          dev.packages.${system}.default
        ];

        shellHook = '''';
      };
    };
}
