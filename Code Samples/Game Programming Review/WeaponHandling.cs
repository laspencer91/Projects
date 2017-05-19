
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum WEAPON
{
	ASSUALTRIFLE, PISTOL, SIZE
};

public class WeaponInventory : MonoBehaviour
{
	/* These are the identifications of the weapon prefabs as
	 * set by dragging and dropping into the inventory manager. 
	 * Every weapon in the game needs to be added here and also
	 * at the bottom in the GetGunPrefab method */
	public Gun assualtRifle;
	public Gun pistol;
	public Gun empty;

	int maxWeapons = 2;  // The maximum slots we have for weapons 
	bool[] hasWeapon;    // This array stores weather we have an item or not. Indexed by the WEAPON Enum.

	public Gun[] weaponSlot { get; set; }

	void Start()
	{
		weaponSlot = new Gun[maxWeapons];
		weaponSlot[0] = empty;   // Start with no weapons in slot 1
		weaponSlot[1] = empty;   // Start with no weapons in slot 2

		InitializeWeaponArray();
	}

	/* Create an array from the specified set of enums above for each index
	   of the enum the boolean is set to initial FALSE. Set to true when a
	   weapon is added to the inventory. */
	void InitializeWeaponArray()
	{
		hasWeapon = new bool[(int)WEAPON.SIZE];

		foreach (WEAPON a in Enum.GetValues(typeof(WEAPON)))
		{
			// Break out of the loop if we are on size so that WEAPON.SIZE is not
			// ADDED to the hasWeapon[] array
			if (a == WEAPON.SIZE)
				break;

			hasWeapon[(int)a] = false;
		}
	}

	/* Return if we have a specified weapon already */
	public bool GetHasWeapon(WEAPON weapon)
	{
		return hasWeapon[(int)weapon];
	}

	/* TODO:  Delete */
	private void Update()
	{
		if (Input.GetKeyDown(KeyCode.P))
		{
			AddWeapon(WEAPON.ASSUALTRIFLE);
		}
	}

	/* Attempt to add a new weapon as specified by the ENUM weapon, if the weapon
	   is already carried by the user do nothing, or else add and swap if we are full
	   on weapons. */
	public void AddWeapon(WEAPON weapon)
	{
		// Exit if we already have the weapon we are trying to add
		if (!hasWeapon[(int)weapon])
		{
			int index = 0;  // TODO: Change this to currently equipped weapon

			/* Find an empty slot to place weapon if it exists */
			for (int i = 0; i < weaponSlot.Length; i++)
			{
				if (weaponSlot[i] == empty)
				{
					index = i;
					break;
				}
			}
			// Get what gun object to add by passing the Enum into this method
			Gun weaponAdd = GetGunPrefab(weapon);

			// If an empty weapon slot was not found previously then we need to drop the current
			// for a swap.
			if (weaponSlot[index] != empty)
				DropWeapon(weaponSlot[index]);    // Drop current Gun in the slot to swap

			weaponSlot[index] = weaponAdd;                 // Assign the new weapon to current slot
			hasWeapon[(int)weaponAdd.ID] = true;           // tell our inventory that we now own this item
		}
	}

	/* Drop a specified weapon */
	public void DropWeapon(Gun weapon)
	{
		hasWeapon[(int)weapon.ID] = false;
	}

	/* Get the Gun object by which weapon enum is passed */
	public Gun GetGunPrefab(WEAPON weapon)
	{
		switch (weapon)
		{
			case WEAPON.ASSUALTRIFLE:
				return assualtRifle;
			case WEAPON.PISTOL:
				return pistol;
			default:
				return empty;
		}
	}
}


public class WeaponSelectManager : MonoBehaviour
{
	Gun currentGun;   // The currently equiped gun
	Gun empty;        // Used as default object to fill empty slot
	GunHolder holder; // Where the gun will spawn
	WeaponInventory weaponInv;

	void Start()
	{
		weaponInv = FindObjectOfType<WeaponInventory>();
		holder = FindObjectOfType<GunHolder>();

		// Grab empty prefab from weaponInventor
		empty = weaponInv.empty;
		currentGun = Instantiate(empty, holder.transform) as Gun;
	}

	/* The newWeapon is Instantiated before this point, if the current gun
	 * is the same as this new one we just destroy that instantiated object or else
	 * we destroy our current gun, and then set the current gun to the new one */
	public void ChangeWeapon(Gun newWeapon)
	{
		if (currentGun.name != newWeapon.name)
		{
			if (currentGun != null)
				Destroy(currentGun.gameObject);

			currentGun = newWeapon;
		}
		else
		{
			Destroy(newWeapon.gameObject);
		}
	}

	void Update()
	{
		/* Equip Items dependent on key
		 * TODO: Create an input manager and new method called change weapon */
		if (Input.GetKeyDown(KeyCode.Alpha1))
		{
			Gun newGun = Instantiate(weaponInv.weaponSlot[0], holder.transform) as Gun;
			ChangeWeapon(newGun);
		}
		if (Input.GetKeyDown(KeyCode.Alpha2))
		{
			Gun newGun = Instantiate(weaponInv.weaponSlot[1], holder.transform) as Gun;
			ChangeWeapon(newGun);
		}
		if (Input.GetKeyDown(KeyCode.Alpha0))
		{
			Gun newGun = Instantiate(empty, holder.transform) as Gun;
			ChangeWeapon(newGun);
		}
	}
}

/* Superclass for all equipable guns.
 * This class implements all of the functionality of the different weapons */
public class Gun : MonoBehaviour
{
	public WEAPON ID { get; set; }

	public int clipSize;
	public int currentAmmoInClip;
	public int fireTimer = 0;

	public float fireRate;
	public float reloadTime;
	public float projectileStrength;
	
	bool currentlyReloading = false;

	void Update()
	{
		if (fireRate != 0)  // Check if I am empty
			if (fireTimer < fireRate)
			{
				++fireTimer;
			}
			else if (Input.GetMouseButton(0))
			{
				if (!currentlyReloading)
				{
					if (currentAmmoInClip > 0)
						Shoot();
					else
						StartCoroutine(FinishReload(reloadTime));
				}
			}
	}

	IEnumerator FinishReload(float time)
	{
		currentlyReloading = true;
		print("RELOADING");
		yield return new WaitForSeconds(time);

		print("RELOADed");
		currentlyReloading = false;
		currentAmmoInClip = clipSize;
	}

	void Shoot()
	{
		--currentAmmoInClip;
		fireTimer = 0; // Reset fireTimer
		print("SHOOTING " + currentAmmoInClip);
	}
}
